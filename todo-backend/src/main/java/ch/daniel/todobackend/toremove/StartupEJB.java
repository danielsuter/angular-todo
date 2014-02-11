package ch.daniel.todobackend.toremove;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.daniel.todobackend.domain.Todo;
import ch.daniel.todobackend.domain.User;

@Startup
@Singleton
public class StartupEJB {
	
	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void contextInitialized() {
		System.out.println("Removing all existing data");
		entityManager.createQuery("DELETE FROM Todo").executeUpdate();
		entityManager.createQuery("DELETE FROM User").executeUpdate();
		
		System.out.println("Creating new sample data");
		TestDataGenerator generator = new TestDataGenerator();
		List<User> users = generator.createUsers();
		persistAll(entityManager, users);
		List<Todo> todos = generator.createTodos(users);
		persistAll(entityManager, todos);
	}
	
	private void persistAll(EntityManager entityManager, Iterable<? extends Object> toPerist) {
		for (Object object : toPerist) {
			entityManager.persist(object);
		}
	}
}
