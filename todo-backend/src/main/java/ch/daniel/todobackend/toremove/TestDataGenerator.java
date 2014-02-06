package ch.daniel.todobackend.toremove;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import ch.daniel.todobackend.domain.Todo;

public class TestDataGenerator {
	public List<Todo> createTodos() {
		List<Todo> todos = new LinkedList<Todo>();
		for(int i = 0; i < 5; i++) {
			Todo todo = new Todo();
			todo.setDescription("my description " + i);
			todo.setDeadline(Calendar.getInstance());
			todo.setDone(i % 2 == 0 ? true : false);
			todo.setAssignee(i % 2 == 0 ? "Dani" : "Nadja");
			todo.setComment("my comment " + i);
			todos.add(todo);
		}
		return todos;
	}
}
