package com.sit.student.dao;

import com.sit.student.domain.Discipline;
import com.sit.student.dto.DisciplineDto;
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
public class DisciplineDaoJdbc implements DisciplineDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public void insert(Discipline discipline) {
        jdbc.update("insert into public.discipline(id_student, name, hours) values (?, ?, ?)",
                discipline.getId_student(), discipline.getName(), discipline.getHours());
    }

    @Override
    public List<DisciplineDto> getByIdStudent(int id) {
        Map<String, Object> params = Collections.singletonMap("pid", id);
        return namedParameterJdbcOperations.query("select id, name, hours from public.discipline where id_student = :pid",
                params, new BeanPropertyRowMapper<>(DisciplineDto.class));
    }
}
