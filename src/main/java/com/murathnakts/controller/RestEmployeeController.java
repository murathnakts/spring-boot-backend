package com.murathnakts.controller;

import com.murathnakts.entity.Employee;
import com.murathnakts.entity.UpdateEmployeeRequest;
import com.murathnakts.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/list")
    public List<Employee> getAllEmployeeList() {
        return employeeService.getAllEmployeeList();
    }

    @GetMapping(path = "/list/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id", required = true) String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "/with-params")
    public List<Employee> getEmployeeWithParams(@RequestParam(name = "firstName", required = false) String firstName,
                                                @RequestParam(name = "lastName", required = false) String lastName) {
        return employeeService.getEmployeeWithParams(firstName, lastName);
    }

    @PostMapping(path = "/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteEmployee(@PathVariable(name = "id", required = true) String id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "/update/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") String id, @RequestBody UpdateEmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }
}
