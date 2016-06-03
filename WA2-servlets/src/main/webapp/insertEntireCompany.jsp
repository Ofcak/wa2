<%@page import="cz.cvut.fel.glassfishjspmysql.InsertEntireCompany"%>
<%@page import="wa2.sem.entities.User"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="wa2.sem.entities.Position"%>
<%@page import="wa2.sem.entities.Company"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        <p><b>Name:</b>
            <%= request.getParameter("name")%>
        </p>
        <form action="insertEntireCompany.jsp" method="POST">
        Company Name: <input type="text" name="Cname" value="FIT"/>
        <br /><br />
        Position1 name: <input type="text" name="position1" value="ucitel"/>
        <br />
        Position2 name: <input type="text" name="position2" value="kuchar"/>
        <br /><br />
        User1 name: <input type="text" name="user1" value="Omacka Karel"/>
        <br />
        User1 position: 
        <select name="user1pos1">
            <option value="-">-</option>
            <option value="1">1</option>
            <option value="2">2</option>
        <select/>
        <br /><br />
        User2 name: <input type="text" name="user2" value="Koder Petr"/>
        <br />
        <select name="user2pos1">
            <option value="-">-</option>
            <option value="1">1</option>
            <option value="2">2</option>
        <select/>
        <br />
        <select name="user2pos2">
            <option value="-">-</option>
            <option value="1">1</option>
            <option value="2">2</option>
        <select/>
        <br />
        <input type="submit" value="Submit" />
        
        <%
            String hotovo = "";
            if(request.getParameter("Cname") != null){
                InsertEntireCompany iec = new InsertEntireCompany();
                iec.insert(request.getParameter("Cname"), 
                        request.getParameter("position1"), 
                        request.getParameter("position2"), 
                        request.getParameter("user1"), 
                        request.getParameter("user2"), 
                        request.getParameter("user1pos1"), 
                        request.getParameter("user2pos1"), 
                        request.getParameter("user2pos2")
                );
                hotovo = "hotovo, company:"+request.getParameter("Cname");
            }
                
                
        %>
        <br/><br/><br/> <b>
        <%=hotovo%>  
        </b>

    </body>
</html>