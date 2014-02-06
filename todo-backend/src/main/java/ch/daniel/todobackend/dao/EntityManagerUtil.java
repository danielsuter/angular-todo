package ch.daniel.todobackend.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManager entityManager = null;
	
	public synchronized static EntityManager get() {
		if(entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoUnit");
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}
}
