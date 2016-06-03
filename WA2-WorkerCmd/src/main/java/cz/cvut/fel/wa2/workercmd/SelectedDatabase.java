/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wa2.workercmd;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import javax.naming.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Mira
 */
public class SelectedDatabase {

    static InitialContext ctx;
    static DataSource ds;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static SessionFactory hibernateSessionFactory;
    
    static{
        //po kazde kdyz se spusti todle, tak se mi premaze databaze 
        //(kvuli hibernate.hbm2ddl.auto - create,  v hibernate.cfg)
        
        Configuration configuration = new Configuration();
        configuration.configure();
        //SessionFactory ourSessionFactory;
        ServiceRegistry serviceRegistry;

        serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).buildServiceRegistry();
        hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public static ResultSet getCountries() throws NamingException, SQLException {
        //this is probably for direct connection to the MySQL
        ctx = new InitialContext();
        ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDataSource");
        //ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Country");

        return rs;
    }

    public static Session getHibernateSession(){
        
        return hibernateSessionFactory.openSession();
    }
}
