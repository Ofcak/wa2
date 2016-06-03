/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.glassfishjspmysql;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wa2.sem.entities.Company;
import wa2.sem.entities.Position;
import wa2.sem.entities.User;

/**
 *
 * @author Mira
 */
public class InsertEntireCompany {
    
    public void insert(String compName, String po1, String po2, String us1, String us2,
            String us1po1, String us2po1, String us2po2){
        
        Company c = createCompany(compName, po1, po2, us1, us2, us1po1, us2po1, us2po2);
        
        insertCompanyToDatabase(c);
    
    }
    
    private Company createCompany(String compName, String po1, String po2, String us1, String us2,
            String us1po1, String us2po1, String us2po2){
        
        
        //create objects
        Company comp = new Company();
        comp.setName(compName);

        Position p1 = new Position();
        p1.setName(po1);
        Position p2 = new Position();
        p2.setName(po2);

        Set<Position> companyPositions = new HashSet<>();
        companyPositions.add(p1);
        companyPositions.add(p2);

        User u1 = new User();
        u1.setName(us1);
        User u2 = new User();
        u2.setName(us2);

        Set<User> companyUsers = new HashSet<>();
        companyUsers.add(u1);
        companyUsers.add(u2);
        
        if(!us1po1.equals("-")){
            Set<Position> u1Positions = new HashSet<>();
            if(!us1po1.equals("1")){
                u1Positions.add(p1);
            } else {
                u1Positions.add(p2);
            }
            u1.setPositions(u1Positions);
        }

        if(!us2po1.equals("-") || !us2po2.equals("-") ){
            Set<Position> u2Positions = new HashSet<>();
            if(!us2po1.equals("1")){
                u2Positions.add(p1);
            } else if(!us2po1.equals("2")){
                u2Positions.add(p2);
            }
            if(!us2po2.equals("1")){
                u2Positions.add(p1);
            } else if(!us2po2.equals("2")){
                u2Positions.add(p2);
            }
            u2.setPositions(u2Positions);
        }

        comp.setPositions(companyPositions);
        comp.setUsers(companyUsers);
        
        return comp;
    }
    
    private void insertCompanyToDatabase(Company c){
        SelectedDatabase db = new SelectedDatabase();
        Session sess = db.getHibernateSession();
        Transaction tx = sess.beginTransaction();    
        
        sess.save(c);
        
        sess.flush();
        tx.commit();
        sess.close();
    }
}
