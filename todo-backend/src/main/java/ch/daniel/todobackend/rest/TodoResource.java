package ch.daniel.todobackend.rest;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.daniel.todobackend.dao.TodoDao;
import ch.daniel.todobackend.domain.Todo;

@Path("todo")
public class TodoResource {
	
	private TodoDao todoDao = new TodoDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() throws IOException {
		List<Todo> todos = todoDao.get();
		return Response.status(200).entity(todos).build();
	}
}
