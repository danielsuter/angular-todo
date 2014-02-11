package ch.daniel.todobackend.toremove;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import ch.daniel.todobackend.domain.Todo;
import ch.daniel.todobackend.domain.User;

public class TestDataGenerator {
	public List<Todo> createTodos(List<User> usersToLink) {
		List<Todo> todos = new LinkedList<Todo>();
		for(int i = 0; i < 5; i++) {
			Todo todo = new Todo();
			todo.setDescription("my description " + i);
			todo.setDeadline(Calendar.getInstance());
			todo.setDone(i % 2 == 0 ? true : false);
			todo.setAssignee(usersToLink.get(i % 3));
			todo.setComment("my comment " + i);
			todos.add(todo);
		}
		return todos;
	}
	
	public List<User> createUsers() {
		List<User> users = new LinkedList<User>();
		users.add(new User("Daniel", "suter_daniel@hotmail.com"));
		users.add(new User("Ilija", "suter_daniel@hotmail.com"));
		users.add(new User("Nadja", "suter_daniel@hotmail.com"));
		return users;
	}
}
