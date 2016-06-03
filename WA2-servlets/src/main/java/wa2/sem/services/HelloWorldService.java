/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.services;

/**
 *
 * @author Mira
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
 
@Path("/HelloWorld")
public class HelloWorldService {
 
	@GET
	@Path("/sayHello")
	public String sayHello() {
		return "<h1>Hello World</h1>";
	}
 
}