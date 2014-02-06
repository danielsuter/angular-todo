package ch.daniel.todobackend.dao;

import java.util.List;

import ch.daniel.todobackend.domain.Todo;

public class TodoDao {
	
	public List<Todo> get() {
		return EntityManagerUtil.get().createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
	}
}
