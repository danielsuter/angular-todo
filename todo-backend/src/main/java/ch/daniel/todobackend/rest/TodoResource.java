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

import ch.daniel.todobackend.rest.domain.Todo;

@Path("todo")
public class TodoResource {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() throws IOException {
		List<Todo> todos = getTodos();
		
		return Response.status(200).entity(todos).build();
	}

	private List<Todo> getTodos() {
		List<Todo> todos = new LinkedList<Todo>();
		for(int i = 0; i < 5; i++) {
			Todo todo = new Todo();
			todo.setDescription("my description " + i);
			todo.setDeadline(Calendar.getInstance());
			todo.setDone(false);
			todo.setAssignee(i % 2 == 0 ? "Dani" : "Nadja");
			todo.setComment("my comment " + i);
			todos.add(todo);
		}
		return todos;
	}
}
