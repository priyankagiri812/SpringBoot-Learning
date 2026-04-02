package com.example.student_mgmt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.student_mgmt.dto.StudentDTO;
import com.example.student_mgmt.entity.Student;
import com.example.student_mgmt.mapper.StudentMapper;
import com.example.student_mgmt.repository.StudentRepository;
import com.example.student_mgmt.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepo;
	private StudentMapper studentMapper;

	public StudentServiceImpl(StudentRepository studentRepo, StudentMapper studentMapper) {
		super();
		this.studentRepo = studentRepo;
		this.studentMapper = studentMapper;
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		
		List<Student> students = studentRepo.findAll();

		return students.stream().map(student -> studentMapper.toDTO(student)).toList();

	}

	@Override
	public void createStudent(StudentDTO studentDTO) {

		Student student = studentMapper.toEntity(studentDTO);

		studentRepo.save(student);
	}

	@Override
	public void updateStudent(StudentDTO studentDTO) {

		Student student = studentMapper.toEntity(studentDTO);

		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setEmail(studentDTO.getEmail());

		studentRepo.save(student);

	}

	@Override
	public StudentDTO getStudentById(Long studentId) {

		Student student = studentRepo.findById(studentId).get();

		StudentDTO studentDTO = studentMapper.toDTO(student);

		return studentDTO;
	}

	@Override
	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub

		studentRepo.deleteById(studentId);

	}

}
