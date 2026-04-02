package com.example.student_mgmt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student_mgmt.dto.StudentDTO;
import com.example.student_mgmt.service.StudentService;

import jakarta.validation.Valid;


@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// Handler method for getting the list of all students
	@GetMapping("/students")
	public String getAllStudents(Model model) {

		List<StudentDTO> students = studentService.getAllStudents();

		model.addAttribute("students", students);

		return "students";

	}

	// Handler method for creating a student
	@GetMapping("/students/new")
	public String newStudent(Model model) {

		StudentDTO studentDTO = new StudentDTO();

		model.addAttribute("student", studentDTO);

		return "create-student";

	}

	// Handler method for saving the student to DB
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") StudentDTO studentDTO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("student", studentDTO);

			return "create-student";
		}

		studentService.createStudent(studentDTO);

		return "redirect:/students";

	}

	// Handler method for creating a student
	@GetMapping("/students/{studentId}/edit")
	public String editStudent(@PathVariable Long studentId, Model model) {

		StudentDTO studentDTO = studentService.getStudentById(studentId);

		model.addAttribute("student", studentDTO);

		return "edit-student";

	}

	// Handler method for updating a student in DB
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable Long studentId, @ModelAttribute("student") StudentDTO studentDTO) {

		studentDTO.setId(studentId);

		studentService.updateStudent(studentDTO);

		return "redirect:/students";

	}

	// Handler method for deleting a student
	@GetMapping("/students/{studentId}/delete")
	public String deleteStudent(@PathVariable Long studentId) {

		studentService.deleteStudent(studentId);

		return "redirect:/students";

	}

}
