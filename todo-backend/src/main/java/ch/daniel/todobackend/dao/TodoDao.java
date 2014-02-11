package ch.daniel.todobackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.daniel.todobackend.domain.Todo;

@Stateless
public class TodoDao {
	@PersistenceContext(unitName="todoUnit")
	private EntityManager entityManager;

	public List<Todo> get() {
		return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
	}

	public void persist(Todo todo) {
		entityManager.persist(todo);
	}

	public void remove(long id) {
		Todo todoToRemove = entityManager.find(Todo.class, id);
		entityManager.remove(todoToRemove);
	}

	public void update(Todo todo) {
		entityManager.merge(todo);
	}
}
