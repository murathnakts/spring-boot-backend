package com.murathnakts.config;

import com.murathnakts.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Employee> employeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1", "Murathan", "Akta"));
        employeeList.add(new Employee("2", "Murathan", "Aktas"));
        employeeList.add(new Employee("3", "Murathann", "Aktas"));
        return employeeList;
    }
}
