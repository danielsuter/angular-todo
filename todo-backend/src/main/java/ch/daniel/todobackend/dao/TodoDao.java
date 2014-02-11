package ch.daniel.todobackend.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.daniel.todobackend.domain.Todo;
import ch.daniel.todobackend.domain.User;

@Stateless
public class TodoDao {
	@PersistenceContext(unitName = "todoUnit")
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

	public Map<User, List<Todo>> getOverDueTodos() {
		Map<User, List<Todo>> result = new LinkedHashMap<>();

		TypedQuery<Todo> query = entityManager.createQuery("SELECT t FROM Todo t WHERE t.deadline < now()", Todo.class);
		List<Todo> resultList = query.getResultList();
		for (Todo todo : resultList) {
			if (!result.containsKey(todo.getAssignee())) {
				result.put(todo.getAssignee(), new LinkedList<Todo>());
			}
			result.get(todo.getAssignee()).add(todo);
		}

		return result;
	}
}
