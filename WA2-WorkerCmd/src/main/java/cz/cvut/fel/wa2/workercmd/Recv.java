
package cz.cvut.fel.wa2.workercmd;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wa2.sem.entities.Company;
import wa2.sem.entities.Position;
import wa2.sem.entities.Shift;
import com.rabbitmq.client.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mira
 */
public class Recv {

    private final static String QUEUE_NAME = "planshift";
    private static Channel channel;
    private static Connection connection;
    private final static int randomWorkerID = (int) (Math.random() * 1000);
    private static String consumerTag;

    public static void init() {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.basicQos(1);
        } catch (IOException ex) {
            Logger.getLogger(Recv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Recv.class.getName()).log(Level.SEVERE, null, ex);
        }

        run();
    }

    public static void run() {

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Worker [" + randomWorkerID + "] Waiting for messages.");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    Session sess = SelectedDatabase.getHibernateSession();
                    try {
                        Thread.sleep(8000);

                        String message = new String(body, "UTF-8");
                        System.out.println("Worker [" + randomWorkerID + "] Received '" + message + "'");

                        //Session sess = SelectedDatabase.getHibernateSession();
                        Transaction tx = sess.beginTransaction();
                        Shift s = new Shift();
                        s.setNote(message);
                        s.setCompany((Company) sess.load(Company.class, 1));
                        s.setPosition((Position) sess.load(Position.class, 1/*(int)(Math.random()*3)*/));
                        s.setShiftLength(randomWorkerID);
                        s.setShiftStart(new Date(2016, 5, 30, 12, 12 + ((int) (Math.random() * 1000)), 12));

                        sess.save(s);

                        sess.flush();
                        tx.commit();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Recv.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception e) {
                        System.out.println("some exception at receiver");
                        e.printStackTrace();
                    } finally {
                        sess.close();
                    }
                }
            };

            consumerTag = channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException ex) {
            Logger.getLogger(Recv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
