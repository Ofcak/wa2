/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.services;

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
import wa2.sem.entities.DTO.UserDTO;
import wa2.sem.entities.User;

/**
 *
 * @author Mira
 */
@Stateless
@Path("/User")
public class UserFacadeREST extends AbstractFacade<User> {

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    //@Produces({"application/xml", "application/json"})
    public void create(UserDTO entityDTO ) {
        
        System.out.println("UserDTO:"+entityDTO.toString());
        User entity = new User(entityDTO);

        super.create(entity);
        //return entityDTO;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, UserDTO entityDTO, @Context HttpServletResponse response) {
        
        User entity = new User(entityDTO);
        
        super.edit(entity);
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id, @Context HttpServletResponse response) {
        
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        //@Context HttpServletResponse response
        //response.addHeader("Access-Control-Allow-Origin", "*");
        return super.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        
        //response.addHeader("Access-Control-Allow-Origin", "*");
        return super.findAll();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }

//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
    
}
