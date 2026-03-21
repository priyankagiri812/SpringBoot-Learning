package com.example.employee_mgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name", nullable=false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=100)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	//LAZY - Department data is loaded only when needed
	@ManyToOne(fetch = FetchType.LAZY) //Many Employees → One Department 
	@JoinColumn(name="department_id", nullable=false)
	private Department department;
}
