package ch.daniel.todobackend.rest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ch.daniel.todobackend.domain.Todo;

@Path("hello")
public class TestResource {
	
	static EntityManager entityManager;
	
	static {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample");
		entityManager = emf.createEntityManager();
	}
	
	@GET
	@Path("jpa")
	public Response test2() {
		entityManager.getTransaction().begin();
		Todo todo = new Todo();
		todo.setAssignee("Nadja");
		todo.setComment("jpa comment");
		todo.setDeadline(Calendar.getInstance());
		todo.setDone(false);
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
		
		return Response.status(200).build();
	}
	
	@GET
	public Response test() {

		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext  = (Context)ctx.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/TodoDB");
			Connection connection = ds.getConnection();
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from todo");
			while(resultSet.next()) {
				System.out.println(resultSet.getString("description"));
			}
			
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).build();
	}
}
