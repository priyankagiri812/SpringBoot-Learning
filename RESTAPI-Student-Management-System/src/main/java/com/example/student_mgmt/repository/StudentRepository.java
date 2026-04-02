package com.example.student_mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student_mgmt.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
