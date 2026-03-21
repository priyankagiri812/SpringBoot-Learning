package com.example.employee_mgmt.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.employee_mgmt.dto.EmployeeDTO;
import com.example.employee_mgmt.entity.Department;
import com.example.employee_mgmt.entity.Employee;
import com.example.employee_mgmt.exception.BadRequestException;
import com.example.employee_mgmt.exception.ResourceNotFoundException;
import com.example.employee_mgmt.repository.DepartmentRepository;
import com.example.employee_mgmt.repository.EmployeeRepository;
import com.example.employee_mgmt.service.EmployeeService;

@Service

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empRepository;
	private DepartmentRepository deptRepository;
	private ModelMapper modelMapper;
	
	public EmployeeServiceImpl(EmployeeRepository empRepository, DepartmentRepository deptRepository, ModelMapper modelMapper) {
		super();
		this.empRepository = empRepository;
		this.deptRepository = deptRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public EmployeeDTO addEmployees(Long departmentID, EmployeeDTO empDTO) {
		//Retrieve the department object by ID.
		Department department = deptRepository.findById(departmentID)
				.orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));
		//Convert EmployeeDTO to EmployeeEntity
		Employee employee = modelMapper.map(empDTO, Employee.class);
		//Associate Employee entity with Department
		employee.setDepartment(department);
		//Save Employee entity to DB and convert it to DTO.
		EmployeeDTO savedEmployee = modelMapper.map(empRepository.save(employee), EmployeeDTO.class); 
		
		return savedEmployee;
	}


	@Override
	public EmployeeDTO getEmployeeById(Long departmentID, Long empID) {
		//Retrieve the department object by ID.
		Department department = deptRepository.findById(departmentID)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+departmentID));
		//Convert EmployeeDTO to EmployeeEntity
		Employee employee = empRepository.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+empID));
		
		if(employee.getDepartment().getId() != department.getId()) {
			throw new BadRequestException("This employee does not belong department with ID: "+departmentID);
		}
		
		EmployeeDTO empDTO = modelMapper.map(employee, EmployeeDTO.class);
		empDTO.setDepartmentID(employee.getDepartment().getId());
		
		return empDTO;
	}


	@Override
	public List<EmployeeDTO> getEmployessByDept(Long departmentID) {
		//Retrieve the department object by ID.
		List<Employee> employees = empRepository.findByDepartmentId(departmentID);
		
		return employees.stream()
		.map((Employee employee) -> modelMapper.map(employee, EmployeeDTO.class))
		.toList();
		
	}


	@Override
	public EmployeeDTO updateEmployee(Long departmentID, Long empID, EmployeeDTO empDTO) {
		
		//Retrieve the department object by ID.
		Department department = deptRepository.findById(departmentID)
						.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+departmentID));
		
		//Retrieve the employee object by ID.
		Employee employee = empRepository.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+empID));
		
		employee.setDepartment(department);
		employee.setFirstName(empDTO.getFirstName());
		employee.setLastName(empDTO.getLastName());
		employee.setEmail(empDTO.getEmail());
		
		EmployeeDTO savedEmployee = modelMapper.map(empRepository.save(employee), EmployeeDTO.class);
		
		return savedEmployee;
	}


	@Override
	public void deleteEmployee(Long departmentID, Long empID) {
	
		//Retrieve the department object by ID.
		Department department = deptRepository.findById(departmentID)
								.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: "+departmentID));
				
		//Retrieve the employee object by ID.
		Employee employee = empRepository.findById(empID)
						.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+empID));
		
		employee.setDepartment(department);
		
		empRepository.delete(employee);
	}
	
	

}
