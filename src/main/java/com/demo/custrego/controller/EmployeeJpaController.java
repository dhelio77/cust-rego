package com.demo.custrego.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.custrego.exception.EmployeeNotFoundException;
import com.demo.custrego.repository.EmployeeRepository;
import com.demo.custrego.vo.Employee;

@RestController
public class EmployeeJpaController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("id - " + id);
		}
		return employee;
//		Resource<Employee> resource = new Resource<Employee>(employee.get());
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getEmployees());
//		return resource.add(linkTo.withRel("all-users"));
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		employeeRepository.deleteById(id);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> saveUser(@RequestBody Employee employee) {
		Employee saved = employeeRepository.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
