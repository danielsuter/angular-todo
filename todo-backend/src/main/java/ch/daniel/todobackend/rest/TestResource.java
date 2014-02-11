package ch.daniel.todobackend.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.daniel.todobackend.service.MailService;

@Path("test")
public class TestResource {
	
	@EJB
	private MailService mailService;
	
	@GET
	public Response get() {
		mailService.send("suter_daniel@hotmail.com", "Test, ob Shinseikan funktioniert", "Funktioniert es?");
		return Response.status(Status.OK).build();
	}
}
