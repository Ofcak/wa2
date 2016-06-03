<%@ page import="cz.cvut.fel.glassfishjspmysql.SelectedDatabase, java.io.*, javax.naming.*" %>
<%@page import="cz.cvut.fel.glassfishjspmysql.DatabaseFunctions"%>
<html>
    <head><title>Hello world from JSP</title></head>
    <body>
        Fill db
        
        <%
            int random = 5;
            DatabaseFunctions df = new DatabaseFunctions(new SelectedDatabase());
            for(int i=0;i<6;i++){
                random = (int) (Math.random()*200);
                df.fillDB(random);
                //df.addCustomerWithAddress(random);
                //String x = df.addProducer(random);
            }
        %>
        <p>fillDB() ok</p>
        
        <%
            //df.addCustomerWithAddress(random+5);
     
        %>
        <!--p>addCustomerWithAddress() ok</p-->
        
        <%
            //String x = df.addProducer(random + 12);
            //<%=x
        %>
        <!--p>addProducer() ok, x= </p>
        
    </body>
</html>