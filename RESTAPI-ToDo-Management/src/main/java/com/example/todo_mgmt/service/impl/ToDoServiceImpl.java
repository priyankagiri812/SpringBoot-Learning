package com.example.todo_mgmt.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.todo_mgmt.dto.ToDoDTO;
import com.example.todo_mgmt.entity.ToDo;
import com.example.todo_mgmt.repository.ToDoRepository;
import com.example.todo_mgmt.service.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	private ToDoRepository todoRepository;

	private ModelMapper modelMapper;

	public ToDoServiceImpl(ToDoRepository todoRepository, ModelMapper modelMapper) {
		super();
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ToDoDTO addTodo(ToDoDTO dto) {

		ToDo todo = modelMapper.map(dto, ToDo.class);

		ToDo savedTodo = todoRepository.save(todo);

		return modelMapper.map(savedTodo, ToDoDTO.class);

	}

	@Override
	public ToDoDTO getTodo(Long id) {

		ToDoDTO savedTodo = modelMapper.map(todoRepository.findById(id), ToDoDTO.class);

		return savedTodo;
	}

	@Override
	public List<ToDoDTO> getAllTodo() {

		List<ToDo> todos = todoRepository.findAll();

		return todos.stream().map((ToDo todo) -> modelMapper.map(todo, ToDoDTO.class)).toList();

	}

	@Override
	public ToDoDTO updateTodo(Long id, ToDoDTO dto) {

		ToDo todoObj = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Not Found!"));

		todoObj.setTitle(dto.getTitle());
		todoObj.setDescription(dto.getDescription());
		todoObj.setCompleted(dto.isCompleted());

		return modelMapper.map(todoRepository.save(todoObj), ToDoDTO.class);
	}

	@Override
	public void deleteToDo(Long id) {

		ToDo todoObj = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Not Found!"));

		todoRepository.deleteById(id);

	}

	@Override
	public ToDoDTO completeTodo(Long id) {

		ToDo todoObj = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Not Found!"));

		todoObj.setCompleted(true);

		return modelMapper.map(todoRepository.save(todoObj), ToDoDTO.class);
	}

	@Override
	public ToDoDTO inCompleteTodo(Long id) {

		ToDo todoObj = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo Not Found!"));

		todoObj.setCompleted(false);

		return modelMapper.map(todoRepository.save(todoObj), ToDoDTO.class);
	}

}
