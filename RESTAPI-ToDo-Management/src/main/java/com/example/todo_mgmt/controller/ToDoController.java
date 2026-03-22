package com.example.todo_mgmt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_mgmt.dto.ToDoDTO;
import com.example.todo_mgmt.service.ToDoService;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

	private ToDoService todoService;

	public ToDoController(ToDoService todoService) {
		super();
		this.todoService = todoService;
	}

	// Create ToDo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ToDoDTO> addToDo(@RequestBody ToDoDTO dto) {

		return new ResponseEntity<>(todoService.addTodo(dto), HttpStatus.CREATED);
	}

	// Get ToDo by ID
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/{id}")
	public ResponseEntity<ToDoDTO> getToDo(@PathVariable Long id) {

		return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
	}

	// Get All Todos
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<ToDoDTO>> getAllToDo() {

		return new ResponseEntity<List<ToDoDTO>>(todoService.getAllTodo(), HttpStatus.OK);
	}

	// Update ToDo Request
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ToDoDTO> updateToDo(@PathVariable Long id, @RequestBody ToDoDTO dto) {

		return new ResponseEntity<>(todoService.updateTodo(id, dto), HttpStatus.CREATED);
	}

	// Delete Request
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable Long id) {

		todoService.deleteToDo(id);

		return ResponseEntity.ok("ToDo Deleted.");

	}

	// Complete ToDo
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("/{id}/complete")
	public ResponseEntity<ToDoDTO> completeToDo(@PathVariable Long id) {

		return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
	}

	// Incomplete ToDo
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("/{id}/inscomplete")
	public ResponseEntity<ToDoDTO> inCompleteToDo(@PathVariable Long id) {

		return new ResponseEntity<>(todoService.inCompleteTodo(id), HttpStatus.OK);
	}
}
