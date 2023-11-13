package com.sit.student.dto;

import com.sit.student.domain.Student;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DisciplineDto {
    private Integer id;
    private String name;
    private Integer hours;
}
