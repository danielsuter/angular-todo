package ch.daniel.todobackend.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.daniel.todobackend.service.MailService;
import ch.daniel.todobackend.service.NotifyService;
import ch.daniel.todobackend.toremove.StartupEJB;

@Path("test")
public class TestResource {
	
	@EJB
	private MailService mailService;
	
	@EJB
	private NotifyService notifyService;
	
	@EJB
	private StartupEJB startupEJB; 
	
	@Path("/sendmails")
	@GET
	public Response get() {
		notifyService.sendMails();
		return Response.status(Status.OK).build();
	}
	
	@Path("/reset-test-data")
	@GET
	public Response resetTestData() {
		startupEJB.contextInitialized();
		return Response.status(Status.OK).build();
	}
}