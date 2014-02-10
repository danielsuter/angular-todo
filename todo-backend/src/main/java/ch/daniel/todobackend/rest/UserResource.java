package ch.daniel.todobackend.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.daniel.todobackend.dao.UserDao;
import ch.daniel.todobackend.domain.User;

@Path("user")
public class UserResource {
	private UserDao userDao = new UserDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		List<User> todos = userDao.get();
		return Response.status(200).entity(todos).build();
	}
}
