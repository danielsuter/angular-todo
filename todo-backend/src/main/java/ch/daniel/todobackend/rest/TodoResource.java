package ch.daniel.todobackend.rest;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ch.daniel.todobackend.dao.TodoDao;
import ch.daniel.todobackend.domain.Todo;

@Path("todo")
public class TodoResource {
	
	@EJB
	private TodoDao todoDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() throws IOException {
		List<Todo> todos = todoDao.get();
		return Response.status(200).entity(todos).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Todo toCreateOrUpdate) {
		if(toCreateOrUpdate.getId() == null) {
			todoDao.persist(toCreateOrUpdate);
		} else {
			todoDao.update(toCreateOrUpdate);
		}
		
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		todoDao.remove(id);
		return Response.status(Status.OK).build();
	}
}
