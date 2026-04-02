package com.example.student_mgmt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

	private Long id;
	@NotEmpty(message = "This field cannot be empty.")
	private String firstName;

	@NotEmpty(message = "This field cannot be empty.")
	private String lastName;

	@NotEmpty(message = "This field cannot be empty.")
	@Email
	private String email;
}
