<%@page import="cz.cvut.fel.glassfishjspmysql.Queue"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        
        <p><b>Send:</b>
            <%= request.getParameter("message")%>
        </p>
        <form action="sendMQ.jsp" method="POST">
        Message: <input type="text" name="note" value="Moje poznamka">
        <br/>
        Num of messages: <input type="text" name="num" value="5">
        <input type="submit" value="Submit" />

        <%
            String send = "";
            if(request.getParameter("note") != null){
                Queue.send(request.getParameter("note"), request.getParameter("num"));
                send = "Message: "+request.getParameter("note")+", "+request.getParameter("num")+" times.";
            }
        %> <br/> <b>
            <%=send%>
        </b>    
            
    </body>
</html>