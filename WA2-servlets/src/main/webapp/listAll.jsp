<%@page import="java.util.List"%>
<%@page import="wa2.entities.*"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>LIST ALL</title></head>
    <body>
        Use parameter className
        <%
            Car ca;
            Address a;
            Producer p;
            
            //try{
            //Class c = Class.forName("wa2.entities."+request.getParameter("className")+".class");
            Class c = Address.class;
            
            Session sess = SelectedDatabase.getHibernateSession();
            List<Object> entities;
        %>
            <h2><%= c.toString()%>, <%= request.getParameter("className")%> </h2>
        
        <%
            
                entities = sess.createCriteria(c).list();
                int i = 1;
                for(Object o : entities){
                
        %>
        <p><%= i%>. toString: <%= o.toString()%></p>
        <%
                    i++;
                }
            //} catch(ClassNotFoundException e){

                %>
                
                <%
            //}    
            
        %>
        
        <%
                entities = sess.createCriteria(Producer.class).list();
                for(Object o : entities){
                
        %>
        <p><%= i%>. toString: <%= o.toString()%></p>
        <%
                    i++;
                }
                %>
        
    </body>
</html>