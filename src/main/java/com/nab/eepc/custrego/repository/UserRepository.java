package com.nab.eepc.custrego.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nab.eepc.custrego.vo.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
