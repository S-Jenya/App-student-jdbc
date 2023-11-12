package com.sit.student.dao;

import com.sit.student.domain.Discipline;
import com.sit.student.dto.DisciplineDto;

import java.util.List;

public interface DisciplineDao {
    void insert(Discipline discipline);

    List<DisciplineDto> getByIdStudent(int id);
}
