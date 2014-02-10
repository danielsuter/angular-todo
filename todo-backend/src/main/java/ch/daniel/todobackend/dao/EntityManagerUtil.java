package ch.daniel.todobackend.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("todoUnit");;
	
	public synchronized static EntityManager get() {
		return factory.createEntityManager();
	}
}
