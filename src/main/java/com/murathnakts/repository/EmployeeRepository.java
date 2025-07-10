package com.murathnakts.repository;


import com.murathnakts.entity.Employee;
import com.murathnakts.entity.UpdateEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private List<Employee> employeeList;

    public List<Employee> getAllEmployeeList() {
        return employeeList;
    }

    public Employee getEmployeeById(String id) {
        Employee findEmployee = null;
        for (Employee employee : employeeList) {
            if (id.equals(employee.getId())) {
                findEmployee = employee;
                break;
            }
        }
        return findEmployee;
    }

    public List<Employee> getEmployeeWithParams(String firstName, String lastName) {
        List<Employee> employeeWithParams = new ArrayList<>();

        if (firstName == null && lastName == null) {
            return employeeList;
        } else if (firstName != null && lastName == null) {
            for (Employee employee : employeeList) {
                if (employee.getFirstName().equalsIgnoreCase(firstName))
                    employeeWithParams.add(employee);
            }
        } else if (firstName == null) {
            for (Employee employee : employeeList) {
                if (employee.getLastName().equalsIgnoreCase(lastName))
                    employeeWithParams.add(employee);
            }
        } else {
            for (Employee employee : employeeList) {
                if (employee.getFirstName().equalsIgnoreCase(firstName) && employee.getLastName().equalsIgnoreCase(lastName))
                    employeeWithParams.add(employee);
            }
        }
        return employeeWithParams;
    }

    public Employee saveEmployee(Employee employee) {
        employeeList.add(employee);
        return employee;
    }

    public boolean deleteEmployee(String id) {
        Employee findEmployee = getEmployeeById(id);
        if (findEmployee != null) {
            employeeList.remove(findEmployee);
            return true;
        }
        return false;
    }

    public Employee updateEmployee(String id, UpdateEmployeeRequest request) {
        Employee findEmployee = getEmployeeById(id);
        if (findEmployee != null) {
            findEmployee.setFirstName(request.getFirstName());
            findEmployee.setLastName(request.getLastName());
        }
        return findEmployee;
    }
}
