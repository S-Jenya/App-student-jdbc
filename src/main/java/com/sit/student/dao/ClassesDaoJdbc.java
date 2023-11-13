package com.sit.student.dao;

import com.sit.student.domain.Classes;
import com.sit.student.dto.ClassesDto;
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
public class ClassesDaoJdbc implements ClassesDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void insert(Classes classes) {
        jdbc.update("insert into public.classes(id_discipline, name, dtstart) values (?, ?, ?)",
                classes.getId_discipline(), classes.getName(), classes.getDtstart());
    }

    @Override
    public List<ClassesDto> getAllByIdDiscipline(int id) {
        Map<String, Object> params = Collections.singletonMap("pid", id);
        return namedParameterJdbcOperations.query("select name, dtstart, dtend, grade, description from public.classes where id_discipline = :pid",
                params,
                new BeanPropertyRowMapper<>(ClassesDto.class));
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from public.classes where id = :id", params);
    }
}
