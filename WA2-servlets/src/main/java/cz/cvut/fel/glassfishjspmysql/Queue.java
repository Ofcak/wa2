/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.glassfishjspmysql;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {

    private final static String QUEUE_NAME = "planshift";
    private static Channel channel;
    private static Connection connection;
    
    static {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            
            //todle nastavit, aby nepridelovala fronta workerz k taskum predem,
            //ale abz si brali task kterej je aktualne dostupnej
            channel.basicQos(1);
        } catch (IOException ex) {
            System.out.println("INITIALIZATION EXCEPTION");
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (TimeoutException ex) {
            System.out.println("INITIALIZATION EXCEPTION");
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    
    /*public static void main(String[] argv) throws Exception {


        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");


    }*/

    public static void send(String note, String num){
        
        int number = Integer.parseInt(num);
        for(int i=0;i<number;i++){
            send(note+"["+i+"]");
        }
        
    }
    
    public static void send(String message){
        try {
            System.out.println("sending message:"+message);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
        } catch (IOException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void close(){
        try {
            channel.close();    
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
