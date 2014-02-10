package ch.daniel.todobackend.dao;

import java.util.List;

import javax.persistence.EntityManager;

import ch.daniel.todobackend.domain.User;

public class UserDao {
	private EntityManager entityManager = EntityManagerUtil.get();

	public List<User> get() {
		return entityManager.createQuery("SELECT u FROM User u", User.class)
				.getResultList();
	}
}
