package ch.daniel.todobackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.daniel.todobackend.domain.User;

@Stateless
public class UserDao {
	@PersistenceContext(unitName = "todoUnit")
	private EntityManager entityManager;

	public List<User> get() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	public boolean isValidCredentials(String username, String password) {
		TypedQuery<User> query = entityManager.createQuery(
				"SELECT u FROM User u WHERE u.name = :username AND u.password = :password", 
				User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		List<User> user = query.getResultList();
		return user.size() == 1;
	}
}
