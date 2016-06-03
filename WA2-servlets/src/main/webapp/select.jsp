<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="wa2.entities.Car"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        Vyber a vypis vsechna auta od vsrobce Fiat a k nim napis seznam veskersho materislu od vsech dodavatels pro Fiat
        <p><b>Name:</b>
            <%= request.getParameter("IDproducer")%>
        </p>
        <form action="index.jsp" method="GET">
        ID producer: <input type="text" name="IDproducer">
        <input type="submit" value="Submit" />

        <p><b>Result:</b>
            <%= request.getParameter("IDproducer")%>
        </p>
        
        <%
            Session s = SelectedDatabase.getHibernateSession();
            
            Query getCars = s.createSQLQuery("Select c.id, c.name, c.producer_prod_id, p.name FROM producer p"
                    + "JOIN Car c ON p.prod_id = c.producer_prod_id"
                    + "WHERE prod_id = :prod")
                    .setParameter("prod", request.getParameter("IDproducer"));
            List<Object[]> result = getCars.list();
            
            for(Object[] c : result){
                for(int i=0;i<c.length;i++){
                    String text = c[i].toString();
        %>
            <%=text.toString()%>,
        <%
                }
                %><br/><%
            }
        %>
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        <form action="index.jsp" method="POST">
        pFirst Name: <input type="text" name="pfirst_name">
        <br />
        pLast Name: <input type="text" name="plast_name" />
        <input type="submit" value="Submit" />
        
        <%
            ResultSet rs;

            try {
                SelectedDatabase db = new SelectedDatabase();
                rs = db.getCountries();
                while (rs.next()) {
        %>
        <p>todle sem napsal jaaa :]</p>
        <h3>Name: <%= rs.getString("Name")%></h3>
        <h3>Population: <%= rs.getString("Population")%></h3>
        <%
            }
        } catch (SQLException se) {
        %>
        <%= se.getMessage()%>
        <%
        } catch (NamingException ne) {
        %>  
        <%= ne.getMessage()%>
        <%
            }
        %>
    </body>
</html>