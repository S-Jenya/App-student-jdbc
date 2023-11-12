package com.sit.student.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassesDto {
    private String name;
    private Date dtstart;
    private Date dtend;
    private Integer grade;
    private String description;
}
