package com.murathnakts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentIU {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
