package com.example.employee_mgmt.service;

import java.util.List;

import com.example.employee_mgmt.dto.EmployeeDTO;

public interface EmployeeService {

	public EmployeeDTO addEmployees(Long departmentID, EmployeeDTO empDTO);
	
	public EmployeeDTO getEmployeeById(Long departmentID, Long empID);
	
	public EmployeeDTO updateEmployee(Long departmentID, 
									  Long empID,
									  EmployeeDTO empDTO);
	
	public void deleteEmployee(Long departmentID,
							   Long empID);
	
	public List<EmployeeDTO> getEmployessByDept(Long departmentID);
}
