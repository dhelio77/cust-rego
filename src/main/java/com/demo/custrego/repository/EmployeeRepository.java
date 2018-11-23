package com.demo.custrego.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.custrego.vo.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
