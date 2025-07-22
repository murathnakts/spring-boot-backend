package com.murathnakts.repository;

import com.murathnakts.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select date_of_birth from spring.student", nativeQuery = true)
    List<String> getStudentBirthDate();

    @Query(value = "from Student")
    Page<Student> findAllPageable(Pageable pageable);
}
