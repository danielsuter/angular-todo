package ch.daniel.todobackend.dao;

import java.util.List;

import javax.persistence.EntityManager;

import ch.daniel.todobackend.domain.Todo;

public class TodoDao {
	private EntityManager entityManager = EntityManagerUtil.get();
	
	public List<Todo> get() {
		return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
	}
	
	public void persist(Todo todo) {
		entityManager.getTransaction().begin();
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
	}
	
	public void remove(long id) {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		Todo todoToRemove = entityManager.find(Todo.class, id);
		entityManager.remove(todoToRemove);
		entityManager.getTransaction().commit();
	}
}
