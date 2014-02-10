package ch.daniel.todobackend.toremove;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ch.daniel.todobackend.dao.EntityManagerUtil;
import ch.daniel.todobackend.domain.Todo;
import ch.daniel.todobackend.domain.User;

@WebListener
public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		EntityManager entityManager = EntityManagerUtil.get();
		entityManager.getTransaction().begin();
		System.out.println("Removing all existing data");
		entityManager.createQuery("DELETE FROM Todo").executeUpdate();
		entityManager.createQuery("DELETE FROM User").executeUpdate();
		
		
		System.out.println("Creating new sample data");
		TestDataGenerator generator = new TestDataGenerator();
		List<User> users = generator.createUsers();
		persistAll(entityManager, users);
		List<Todo> todos = generator.createTodos(users);
		persistAll(entityManager, todos);
		
		entityManager.getTransaction().commit();
	}
	
	private void persistAll(EntityManager entityManager, Iterable<? extends Object> toPerist) {
		for (Object object : toPerist) {
			entityManager.persist(object);
		}
	}
}
