package com.murathnakts.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "İsim Boş Bırakılamaz!")
    @Min(value = 3)
    @Max(value = 15)
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
