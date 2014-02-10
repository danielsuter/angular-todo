package ch.daniel.todobackend.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("todoUnit");;
	private static ThreadLocal<EntityManager> entityManager = new ThreadLocal<>();
	
	
	public synchronized static EntityManager get() {
		if(entityManager.get() == null) {
			entityManager.set(factory.createEntityManager());
		}
		return entityManager.get();
	}
}
