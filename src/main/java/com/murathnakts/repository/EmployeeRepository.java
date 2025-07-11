package com.murathnakts.repository;

import com.murathnakts.dto.DtoEmployee;
import com.murathnakts.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = """
                SELECT * FROM spring.employee 
                WHERE (:firstName IS NULL OR first_name = :firstName)
                  AND (:lastName IS NULL OR last_name = :lastName)
            """, nativeQuery = true)
    List<Employee> findByFirstNameAndLastName(@Param("firstName") String firstName,
                                              @Param("lastName") String lastName);

}
