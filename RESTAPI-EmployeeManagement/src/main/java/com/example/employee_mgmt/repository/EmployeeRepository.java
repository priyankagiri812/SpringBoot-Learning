package com.example.employee_mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_mgmt.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentID);
}
