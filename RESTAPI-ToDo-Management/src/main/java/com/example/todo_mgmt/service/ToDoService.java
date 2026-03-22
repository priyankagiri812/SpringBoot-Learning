package com.example.todo_mgmt.service;

import java.util.List;

import com.example.todo_mgmt.dto.ToDoDTO;

public interface ToDoService {

	public ToDoDTO addTodo(ToDoDTO dto);

	public ToDoDTO getTodo(Long id);

	public ToDoDTO updateTodo(Long id, ToDoDTO dto);

	public void deleteToDo(Long id);

	public ToDoDTO completeTodo(Long id);

	public ToDoDTO inCompleteTodo(Long id);

	public List<ToDoDTO> getAllTodo();
}
