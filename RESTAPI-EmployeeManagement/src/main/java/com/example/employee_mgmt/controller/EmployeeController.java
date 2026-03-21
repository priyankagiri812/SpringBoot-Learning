package com.example.employee_mgmt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_mgmt.dto.EmployeeDTO;
import com.example.employee_mgmt.service.EmployeeService;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {

	private EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		super();
		this.empService = empService;
	}
	
	@PostMapping("/{departmentID}/employees")
	public EmployeeDTO addEmployees(@PathVariable Long departmentID, @RequestBody EmployeeDTO empDTO) {
		return empService.addEmployees(departmentID, empDTO);
	}
	
	@GetMapping("{departmentID}/employees/{empID}")
	public EmployeeDTO getEmployee(@PathVariable Long departmentID, @PathVariable Long empID) {
		
		return empService.getEmployeeById(departmentID, empID);
	}
	
	@GetMapping("{departmentID}/employees")
	public List<EmployeeDTO> getEmployeByDept(@PathVariable Long departmentID) {
		
		return empService.getEmployessByDept(departmentID);
	}
	
	@PutMapping("{departmentID}/employees/{empID}")
	public EmployeeDTO updateEmployee(@PathVariable Long departmentID, @PathVariable Long empID, @RequestBody EmployeeDTO empDTO) {
		return empService.updateEmployee(departmentID, empID, empDTO);
	}
	
	@DeleteMapping("{departmentID}/employees/{empID}")
	public String deleteEmployee(@PathVariable Long departmentID, @PathVariable Long empID) {
		empService.deleteEmployee(departmentID, empID);
		return "Employee "+empID+" is deleted from department "+departmentID;
	}
}
