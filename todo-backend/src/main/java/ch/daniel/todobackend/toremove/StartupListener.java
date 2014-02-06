package ch.daniel.todobackend.toremove;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ch.daniel.todobackend.dao.EntityManagerUtil;
import ch.daniel.todobackend.domain.Todo;

@WebListener
public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		List<Todo> todos = new TestDataGenerator().createTodos();
		EntityManager entityManager = EntityManagerUtil.get();
		entityManager.getTransaction().begin();
		for (Todo todo : todos) {
			entityManager.persist(todo);
		}
		entityManager.getTransaction().commit();
	}
}
