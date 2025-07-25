package com.murathnakts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudent {
    private String firstName;
    private String lastName;
    private List<DtoLesson> lesson;
    private List<DtoCourse> course;
}
