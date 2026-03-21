package com.example.employee_mgmt.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.employee_mgmt.dto.DepartmentDTO;
import com.example.employee_mgmt.entity.Department;
import com.example.employee_mgmt.exception.ResourceNotFoundException;
import com.example.employee_mgmt.repository.DepartmentRepository;
import com.example.employee_mgmt.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	private DepartmentRepository deptRepository;
	private ModelMapper modelMapper;
	
	public DepartmentServiceImpl(DepartmentRepository deptRepository, ModelMapper modelMapper) {
		super();
		this.deptRepository = deptRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public DepartmentDTO addDepartment(DepartmentDTO dept) {
		
		Department department = modelMapper.map(dept, Department.class);
		
		Department savedDepartment = deptRepository.save(department);
		
		logger.info("Department created successfully with ID: {}", savedDepartment.getId());
		
		return modelMapper.map(savedDepartment, DepartmentDTO.class);
		
	}

	@Override
	public DepartmentDTO getDepartment(Long id) {
		
	    Department department = deptRepository.findById(id)
	            .orElseThrow(() -> {
	                logger.error("Department not found with ID: {}", id);
	                return new ResourceNotFoundException("Department not found");
	            });
		
		logger.info("Department fetched successfully: {}", department.getDepartmentName());
		
		return modelMapper.map(department, DepartmentDTO.class);
	
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {
		
		List<Department> departments = deptRepository.findAll();
		
		logger.info("Total departments found: {}", departments.size());
		 
		return departments.stream()
				.map((Department department) -> modelMapper.map(department, DepartmentDTO.class))
				.toList();
		
	}

	@Override
	public DepartmentDTO updateDepartment(DepartmentDTO dept, Long id) {
		
		//Fetch existing data
		Department department = deptRepository
								.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("Department not found"));
		
		logger.debug("Old values - Name: {}, Description: {}",
	            department.getDepartmentName(),
	            department.getDepartmentDescription());
		
		//Update the data
		department.setDepartmentName(dept.getDepartmentName());
		department.setDepartmentDescription(dept.getDepartmentDescription());
		
		//Save the data
		Department updatedDept = deptRepository.save(department);
		
		logger.info("Department updated successfully with ID: {}", updatedDept.getId());
		
		return modelMapper.map(updatedDept, DepartmentDTO.class);
	}

	@Override
	public void deleteDepartment(Long id) {
	
		if (!deptRepository.existsById(id)) {
	        logger.error("Department not found for deletion. ID: {}", id);
	        throw new ResourceNotFoundException("Department not found");
	    }
		
		deptRepository.deleteById(id);
		
		logger.info("Department deleted successfully with ID: {}", id);
		
	}


}
