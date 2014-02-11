package ch.daniel.todobackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.daniel.todobackend.domain.User;

@Stateless
public class UserDao {
	@PersistenceContext(unitName="todoUnit")
	private EntityManager entityManager;

	public List<User> get() {
		return entityManager.createQuery("SELECT u FROM User u", User.class)
				.getResultList();
	}
}
