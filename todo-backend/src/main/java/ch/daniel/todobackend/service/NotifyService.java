package ch.daniel.todobackend.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ch.daniel.todobackend.dao.TodoDao;
import ch.daniel.todobackend.domain.Todo;
import ch.daniel.todobackend.domain.User;

@Singleton
@Startup
public class NotifyService {
	@EJB
	private TodoDao todoDao;

	@EJB
	private MailService mailService;

	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	@Schedule(minute = "*/5", hour="*")
	public void sendMails() {
		System.out.println("executing scheduler");
		Map<User, List<Todo>> groupedTodos = todoDao.getOverDueTodos();

		for (Entry<User, List<Todo>> todoEntry : groupedTodos.entrySet())
			// TODO exception handling
			sendMail(todoEntry);
	}

	private void sendMail(Entry<User, List<Todo>> todoEntry) {
		{
			User user = todoEntry.getKey();
			List<Todo> todos = todoEntry.getValue();

			String to = user.getEmail();
			String subject = "Überfällige Todos";
			String message = createMessage(todos);

			mailService.send(to, subject, message);
		}
	}

	private String createMessage(List<Todo> todos) {
		StringBuffer message = new StringBuffer();
		message.append("Fällige Todos:\n");
		for (Todo todo : todos) {
			message.append("\n - ").append(DATE_FORMAT.format(todo.getDeadline().getTime())).append("\t")
					.append(todo.getDescription());
		}
		return message.toString();
	}
}
