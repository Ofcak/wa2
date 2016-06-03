
<%@page import="wa2.sem.entities.User"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.service.ServiceRegistry"%>
<%@page import="org.hibernate.service.ServiceRegistryBuilder"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        <p><b>Hibernate:</b>
            <%= request.getParameter("name")%>
        </p>
        
        <%
            Session mysession;
            try {
                Configuration configuration = new Configuration();
                configuration.configure();
                SessionFactory ourSessionFactory;
                ServiceRegistry serviceRegistry;

                serviceRegistry = new ServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).buildServiceRegistry();
                ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
                %> pred session <%
                mysession = ourSessionFactory.openSession();
                
                //mysession = Main.getSession();
                %> mam session <%
                Transaction tx = mysession.beginTransaction();
                %> mam transakci <%
                
                User u = new User();
                u.setName("HibernateUser");

                System.out.println("new user: "+u.toString());
                %> ukladam usera <%
                mysession.save(u);
                %> ulozeno <%
                
                mysession.flush();
                tx.commit();
                mysession.close();
                %> nespadlo to <%
            } catch (Throwable ex) {
                //spadlo to access denied ("java.lang.RuntimePermission" "createClassLoader")
                %> spadlo to 
                <%= ex.getMessage() %> 
                <%
                System.out.println("sout");
                ex.printStackTrace();
            } 
        %>
        <p>todle sem napsal jaaa :]</p>
        
        
    </body>
</html>