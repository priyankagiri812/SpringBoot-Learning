package com.example.student_mgmt.service;

import java.util.List;

import com.example.student_mgmt.dto.StudentDTO;

public interface StudentService {

	public List<StudentDTO> getAllStudents();

	public void createStudent(StudentDTO studentDTO);

	public void updateStudent(StudentDTO studentDTO);

	public StudentDTO getStudentById(Long studentId);

	public void deleteStudent(Long studentId);
}
