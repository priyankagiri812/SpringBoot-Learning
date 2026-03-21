package com.example.employee_mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_mgmt.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
