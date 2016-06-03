<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        <p><b>Name:</b>
            <%= request.getParameter("name")%>
        </p>
        <form action="index.jsp" method="GET">
        First Name: <input type="text" name="first_name">
        <br />
        Last Name: <input type="text" name="last_name" />
        <input type="submit" value="Submit" />
        <ul>
            <li><p><b>First Name:</b>
               <%= request.getParameter("first_name")%>
            </p></li>
            <li><p><b>Last  Name:</b>
               <%= request.getParameter("last_name")%>
            </p></li>
            <li><p><b>pFirst Name:</b>
               <%= request.getParameter("pfirst_name")%>
            </p></li>
            <li><p><b>pLast  Name:</b>
               <%= request.getParameter("plast_name")%>
            </p></li>
        </ul>
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