/**
 * This demo executes the userDao instance
 */
package com.demo.custrego.runner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.custrego.dao.UserDao;
import com.demo.custrego.vo.User;

@Component
public class UserDaoCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private UserDao userDao;

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Delio", new Date());
		userDao.saveUser(user);
		log.info("<<< New user is created: " + user);
	}

}
