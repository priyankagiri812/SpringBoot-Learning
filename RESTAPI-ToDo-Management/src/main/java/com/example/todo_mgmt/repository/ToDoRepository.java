package com.example.todo_mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_mgmt.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
