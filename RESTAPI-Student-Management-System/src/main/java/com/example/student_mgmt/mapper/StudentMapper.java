package com.example.student_mgmt.mapper;

import org.mapstruct.Mapper;

import com.example.student_mgmt.dto.StudentDTO;
import com.example.student_mgmt.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentDTO toDTO(Student student);

	Student toEntity(StudentDTO dto);
}
