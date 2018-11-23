/**
 * This demo executes the userRepository which extends JpaRepository
 */
package com.demo.custrego.runner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.custrego.repository.UserRepository;
import com.demo.custrego.vo.User;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserDaoCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jefrey", new Date());
		userRepository.save(user);
		log.info("<<< New user is created: " + user);
		
		Optional<User> userId = userRepository.findById(user.getId());
		log.info("<<< User is retrieved: " + userId);
		
		List<User> userAll = userRepository.findAll();
		log.info("<<< All users extracted: " + userAll);
	}

}
