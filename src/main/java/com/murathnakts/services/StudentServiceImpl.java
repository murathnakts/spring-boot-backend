package com.murathnakts.services;

import com.murathnakts.entity.Student;
import com.murathnakts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @Override
    public void deleteStudent(Integer id) {
        Student findStudent = getStudentById(id);
        if (findStudent != null) {
            studentRepository.delete(findStudent);
        }
    }

    @Override
    public Student updateStudent(Integer id, Student updateStudent) {
        Student findStudent = getStudentById(id);
        if (findStudent != null) {
            findStudent.setFirstName(updateStudent.getFirstName());
            findStudent.setLastName(updateStudent.getLastName());
            findStudent.setDateOfBirth(updateStudent.getDateOfBirth());
            return studentRepository.save(findStudent);
        }
        return null;
    }
}
