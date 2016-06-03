package cz.cvut.fel.glassfishjspmysql;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.*;
import wa2.sem.entities.Company;
import wa2.sem.entities.User;
import wa2.sem.entities.Position;


/**
 * Created by Martin on 4. 3. 2015.
 */
public class DatabaseFunctions {

    private final SelectedDatabase sd;
    
    public DatabaseFunctions(SelectedDatabase sd){
        this.sd = sd;
    }

    public void fillDB(int random) {
        final Session session = sd.getHibernateSession();
        try {
            Transaction tx = session.beginTransaction();

            User u = new User();
            u.setName("Mira"+random);
            //session.save(producer);
            User u2 = new User();
            u2.setName("Ucitel"+random);
            
            
            Position p = new Position();
            p.setName("Delnik"+random);
            
            Position p2 = new Position();
            p2.setName("Uklizec"+random);
            
            Position p3 = new Position();
            p3.setName("Ucitel"+random);
            
            Set<Position> spMira = new HashSet<>();
            spMira.add(p);
            spMira.add(p2);
            
            u.setPositions(spMira);
            
            Set<Position> spUcitel = new HashSet<>();
            spUcitel.add(p3);
            
            u2.setPositions(spUcitel);
            
            Set<Position> spFEL = new HashSet<>();
            spFEL.add(p);
            spFEL.add(p2);
            spFEL.add(p3);
            
            Set<User> suFEL = new HashSet<>();
            suFEL.add(u);
            suFEL.add(u2);
            
            Company c = new Company();
            c.setName("FEL"+random);
            c.setPositions(spFEL);
            c.setUsers(suFEL);
            
            session.save(c);

            session.flush();
            tx.commit();
        } finally {
            session.close();
        }
    }

//    public void readDB() {
//        Session session = sd.getHibernateSession();
//        
//        Car car = (Car) session.get(Car.class, 1);
//        System.out.println("car: "+car.toString());
//        
//        Customer cust = (Customer) session.get(Customer.class, 2);
//        System.out.println("cust: "+cust.toString());
//        
//        session.close();
//    }
//
//    public void addCustomerWithAddress(int random) {
//        final Session session = sd.getHibernateSession();
//        try {
//            Transaction tx = session.beginTransaction();
//
//            Address addr = new Address();
//            addr.setCity("PragueXXX"+random);
//            addr.setStreet("TechnickaXXX"+random);
//            addr.setHouseNumber(10);
//            System.out.println("new address: "+addr.toString());
//            //session.save(addr);
//            
//            Customer cust = new Customer();
//            cust.addAddress(addr);
//            cust.setName("PepaXXX"+random);
//            System.out.println("new customer: "+cust.toString());
//            //session.save(cust);
//
//            Phone ph = new Phone();
//            ph.setNumber("222 515 876XXX"+random);
//            ph.setType(PhoneType.HOME);
//            //session.save(ph);
//            
//            //super, todle automaticky updatne tohodle customera.
//            cust.addPhone(ph);
//            cust.addPhone(new Phone(PhoneType.MOBILE, "1513 456 8XXXX2"+random));
//            session.save(cust);
//            
//            Car c = new Car("SomeCar"+random);
//            session.save(c);
//            
//            session.save(new Car("fastCar"+random));
//            
//            session.flush();
//            tx.commit();
//            
//            
//            /*System.out.println("Delete phone - Enter");
//            sc.nextLine();
//            tx = session.beginTransaction();    
//            
//            cust.removePhone(ph);
//            session.save(cust);
//            
//            session.flush();
//            tx.commit();
//            
//            
//            System.out.println("Delete customer - Enter");
//            sc.nextLine();
//            tx = session.beginTransaction();    
//            
//            session.delete(cust);
//            
//            session.flush();
//            tx.commit();*/
//        } finally {
//            session.close();
//        }
//    }
//    
//    
//    
//    public String addProducer(int random){
//        final Session session = sd.getHibernateSession();
//        try {
//            Transaction tx = session.beginTransaction();
//
//            Producer p1 = new Producer("p1"+random);
//            session.save(new Producer("p2"+random));
//            session.save(new Producer("p3"+random));
//
//            Supplier s1 = new Supplier("s1"+random);
//            Supplier s2 = new Supplier("s2"+random);
//            Supplier s3 = new Supplier("s3"+random);
//            
//            Set<Supplier> supSet = new HashSet<>();
//            supSet.add(s1);
//            supSet.add(s2);
//            supSet.add(s3);
//            
//            Material m1 = new Material("Wood"+random);
//            Material m2 = new Material("m2"+random);
//            Set<Material> matSet = new HashSet<>();
//            matSet.add(m2);
//            matSet.add(m1);
//            
//            s2.setMaterials(matSet);
//            
//            p1.setSuppliers(supSet);
//            session.save(p1);
//
//            
//            
//            
//            session.flush();
//            tx.commit();
//        } catch(Exception e){
//            return "KO";
//        } finally {
//            session.close();
//        }
//        return "ok";
//    }
//    
//    public List<Object> getAllEntitites(Class c){
//        Session session = sd.getHibernateSession();
//        List<Object> list = session.createCriteria(c).list();
//        session.close();
//        return list;
//    }

}
