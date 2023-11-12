package com.sit.student.dao;

import com.sit.student.domain.Student;
import com.sit.student.dto.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class StudentDaoJdbc implements StudentDao{

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert(Student student) {
        jdbc.update("insert into student(sname, fname, pname) values (?, ?, ?)",
                student.getSname(), student.getFname(), student.getPname());
    }

    @Override
    public StudentDto getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from student where id = :id", params, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAll() {
        return jdbc.query("select * from public.student",
                new BeanPropertyRowMapper<>(StudentDto.class));
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from student where id = :id", params);
    }
}
