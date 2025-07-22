package com.murathnakts.services;

import com.murathnakts.dto.DtoStudent;
import com.murathnakts.dto.DtoStudentIU;
import com.murathnakts.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {

    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);

    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(Integer id);

    public void deleteStudent(Integer id);

    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU);

    public List<String> getStudentBirthDate();

    public Page<Student> findAllPageable(Pageable pageable);

    public List<DtoStudent> toDtoList(List<Student> studentList);
}
