/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.services;

import cz.cvut.fel.glassfishjspmysql.Queue;
import cz.cvut.fel.glassfishjspmysql.SelectedDatabase;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.hibernate.Query;
import org.hibernate.Session;
import wa2.sem.entities.DTO.MyMessage;
import wa2.sem.entities.DTO.UserDTO;
import wa2.sem.entities.User;

/**
 *
 * @author Mira
 */
@Stateless
@Path("/sendMQ")
public class SendMQ {


    @POST
    @Consumes({"application/xml", "application/json"})
    //@Produces({"application/xml", "application/json"})
    public void create(MyMessage myMessage ) {
        Queue.send(myMessage.getNote(), myMessage.getNum());

    }
    
    @GET
    @Produces({"application/json"})
    public List read(){
        Session sess = SelectedDatabase.getHibernateSession();
        Query q = sess.createQuery(
                 "FROM wa2.sem.entities.Shift s "
                + "LEFT OUTER JOIN wa2.sem.entities.Company c "
                + "LEFT OUTER JOIN wa2.sem.entities.User u "
                + "WHERE s.company=:abc");
        q.setParameter("abc",1);
        List list = q.list();
        return list;
    }

}
