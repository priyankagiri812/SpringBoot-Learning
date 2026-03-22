package com.example.todo_mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_mgmt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
