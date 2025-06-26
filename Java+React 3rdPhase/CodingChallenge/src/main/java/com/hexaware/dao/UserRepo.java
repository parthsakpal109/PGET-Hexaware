package com.hexaware.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	User findByUsername(String username);
    boolean existsByUsername(String username);
}