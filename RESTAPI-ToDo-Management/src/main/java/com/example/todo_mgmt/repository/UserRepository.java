package com.example.todo_mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_mgmt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Boolean existsByemail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

}
