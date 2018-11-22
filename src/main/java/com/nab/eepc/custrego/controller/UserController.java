package com.nab.eepc.custrego.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nab.eepc.custrego.dao.UserDao;
import com.nab.eepc.custrego.exception.UserNotFoundException;
import com.nab.eepc.custrego.vo.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userDao.retrieveUsers();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		User user = userDao.retrieveUser(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		User savedUser = userDao.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
