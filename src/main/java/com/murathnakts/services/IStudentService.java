package com.murathnakts.services;

import com.murathnakts.dto.DtoStudent;
import com.murathnakts.dto.DtoStudentIU;

import java.util.List;

public interface IStudentService {

    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);

    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(Integer id);

    public void deleteStudent(Integer id);

    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU);

    public List<String> getStudentBirthDate();
}
