package com.murathnakts.services.impl;

import com.murathnakts.dto.DtoCourse;
import com.murathnakts.dto.DtoLesson;
import com.murathnakts.dto.DtoStudent;
import com.murathnakts.dto.DtoStudentIU;
import com.murathnakts.entity.Course;
import com.murathnakts.entity.Lesson;
import com.murathnakts.entity.Student;
import com.murathnakts.repository.StudentRepository;
import com.murathnakts.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        if (student.isPresent()) {
            BeanUtils.copyProperties(student.get(), dtoStudent);
            if (student.get().getLesson() != null) {
                List<DtoLesson> dtoLessonList = new ArrayList<>();
                for (Lesson lesson : student.get().getLesson()) {
                    DtoLesson dtoLesson = new DtoLesson();
                    BeanUtils.copyProperties(lesson, dtoLesson);
                    dtoLessonList.add(dtoLesson);
                }
                dtoStudent.setLesson(dtoLessonList);
            }
            if (student.get().getCourse() != null) {
                List<DtoCourse> dtoCourseList = new ArrayList<>();
                for (Course course : student.get().getCourse()) {
                    DtoCourse dtoCourse = new DtoCourse();
                    BeanUtils.copyProperties(course, dtoCourse);
                    dtoCourseList.add(dtoCourse);
                }
                dtoStudent.setCourse(dtoCourseList);
            }
        }
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

    @Override
    public Page<Student> findAllPageable(Pageable pageable) {
        return studentRepository.findAllPageable(pageable);
    }

    @Override
    public List<DtoStudent> toDtoList(List<Student> studentList) {
        List<DtoStudent> dtoStudentList = new ArrayList<>();

        for (Student student : studentList) {
            DtoStudent dtoStudent = new DtoStudent();
            List<DtoLesson> dtoLessonList = new ArrayList<>();
            List<DtoCourse> dtoCourseList = new ArrayList<>();
            for (Lesson lesson : student.getLesson()) {
                DtoLesson dtoLesson = new DtoLesson();
                BeanUtils.copyProperties(lesson, dtoLesson);
                dtoLessonList.add(dtoLesson);
            }
            for (Course course : student.getCourse()) {
                DtoCourse dtoCourse = new DtoCourse();
                BeanUtils.copyProperties(course, dtoCourse);
                dtoCourseList.add(dtoCourse);
            }
            dtoStudent.setCourse(dtoCourseList);
            dtoStudent.setLesson(dtoLessonList);
            BeanUtils.copyProperties(student, dtoStudent);
            dtoStudentList.add(dtoStudent);
        }

        return dtoStudentList;
    }
}
