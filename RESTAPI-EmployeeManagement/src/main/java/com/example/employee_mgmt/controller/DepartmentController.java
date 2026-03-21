package com.example.employee_mgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_mgmt.dto.DepartmentDTO;
import com.example.employee_mgmt.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	private DepartmentService deptService;

	public DepartmentController(DepartmentService deptService) {
		super();
		this.deptService = deptService;
	}
	
	@PostMapping
	public DepartmentDTO addDepartment(@RequestBody DepartmentDTO dto){
		logger.info("Starting department creation process. Name: {}", dto.getDepartmentName());
		return deptService.addDepartment(dto);
		
	}
	
	@GetMapping("/{id}")
	public DepartmentDTO getDepartments(@PathVariable Long id) {
		logger.info("Fetching department with ID: {}", id);
		return deptService.getDepartment(id);
	}
	
	@GetMapping
	public List<DepartmentDTO> getAllDepartments(){
		logger.info("Fetching all departments");
		return deptService.getAllDepartments();
	}
	
	@PutMapping("/{id}")
	public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO dto, @PathVariable Long id) {
		logger.info("Updating department with ID: {}", id);
		return deptService.updateDepartment(dto,id);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		logger.info("Deleting department with ID: {}", id);
		deptService.deleteDepartment(id);
		return "Department deleted.";
	}
}
