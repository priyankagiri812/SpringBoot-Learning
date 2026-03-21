package com.example.employee_mgmt.service;

import java.util.List;

import com.example.employee_mgmt.dto.DepartmentDTO;

public interface DepartmentService {

	public DepartmentDTO addDepartment(DepartmentDTO dept);
	
	public DepartmentDTO getDepartment(Long id);
	
	public DepartmentDTO updateDepartment(DepartmentDTO dept, Long id);
	
	public void deleteDepartment(Long id);
	
	public List<DepartmentDTO> getAllDepartments();
}
