package com.murathnakts.services.impl;

import com.murathnakts.dto.DtoStudent;
import com.murathnakts.dto.DtoStudentIU;
import com.murathnakts.entity.Student;
import com.murathnakts.repository.StudentRepository;
import com.murathnakts.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {
        DtoStudent dtoStudent = new DtoStudent();
        Student student = new Student();
        BeanUtils.copyProperties(dtoStudentIU, student);
        Student savedStudent = studentRepository.save(student);
        BeanUtils.copyProperties(savedStudent, dtoStudent);
        return dtoStudent;
    }

    @Override
    public List<DtoStudent> getAllStudents() {
        List<DtoStudent> dtoStudentList = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            DtoStudent dtoStudent = new DtoStudent();
            BeanUtils.copyProperties(student, dtoStudent);
            dtoStudentList.add(dtoStudent);
        }
        return dtoStudentList;
    }

    @Override
    public DtoStudent getStudentById(Integer id) {
        DtoStudent dtoStudent = new DtoStudent();
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(value -> BeanUtils.copyProperties(value, dtoStudent));
        return dtoStudent;
    }

    @Override
    public void deleteStudent(Integer id) {
        Optional<Student> findStudent = studentRepository.findById(id);
        findStudent.ifPresent(student -> studentRepository.delete(student));
    }

    @Override
    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU) {
        DtoStudent dtoStudent = new DtoStudent();
        Optional<Student> findStudent = studentRepository.findById(id);
        if (findStudent.isPresent()) {
            Student student = findStudent.get();
            student.setFirstName(dtoStudentIU.getFirstName());
            student.setLastName(dtoStudentIU.getLastName());
            studentRepository.save(student);
            BeanUtils.copyProperties(student, dtoStudent);
            return dtoStudent;
        }
        return null;
    }

    @Override
    public List<String> getStudentBirthDate() {
        return studentRepository.getStudentBirthDate();
    }
}
