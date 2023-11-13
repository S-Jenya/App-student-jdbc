package com.sit.student.dao;

import com.sit.student.domain.Student;
import com.sit.student.dto.StudentDto;

import java.util.List;

public interface StudentDao {

    void insert(Student student);

    StudentDto getById(int id);

    List<StudentDto> getAll();

    void deleteById(int id);
}
