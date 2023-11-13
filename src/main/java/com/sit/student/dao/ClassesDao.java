package com.sit.student.dao;

import com.sit.student.domain.Classes;
import com.sit.student.dto.ClassesDto;

import java.util.List;

public interface ClassesDao {

    void insert(Classes classes);

    List<ClassesDto> getAllByIdDiscipline(int id);

    void deleteById(int id);
}
