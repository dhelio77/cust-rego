package com.nab.eepc.custrego.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nab.eepc.custrego.vo.User;

@Component	// define a bean
@Repository // store to db
/**
 * All methods are transactional inside UserDao.
 * However, only those objects (user) that are in the "persistence context" are tracked by the "entity manager".
 * e.g. entityManager.persist(user)
 */
@Transactional 
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User(1, "Delio", new Date()));
		users.add(new User(2, "Alma", new Date()));
		users.add(new User(3, "Andwele", new Date()));
	}

	/**
	 * Get all users
	 * 
	 * @return
	 */
	public List<User> retrieveUsers() {
		return users;
	}

	/**
	 * Add a user
	 * 
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {
		entityManager.persist(user);
		return user;
	}

	/**
	 * Get one user
	 * 
	 * @param id
	 * @return
	 */
	public User retrieveUser(Integer id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
