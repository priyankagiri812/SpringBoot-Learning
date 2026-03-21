package com.example.employee_mgmt.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO{
	
	private Long id;
	private String departmentName;
	private String departmentDescription;
	private List<EmployeeDTO> employees;
}
