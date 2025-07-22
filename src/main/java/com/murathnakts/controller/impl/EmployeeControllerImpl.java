package com.murathnakts.controller.impl;

import com.murathnakts.controller.BaseController;
import com.murathnakts.controller.IEmployeeController;
import com.murathnakts.dto.DtoEmployee;
import com.murathnakts.dto.DtoEmployeeIU;
import com.murathnakts.entity.RootEntity;
import com.murathnakts.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl extends BaseController implements IEmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping(path = "/list")
    public RootEntity<List<DtoEmployee>> getAllEmployeeList() {
        return success(employeeService.getAllEmployeeList());
    }

    @GetMapping(path = "/list/{id}")
    public RootEntity<DtoEmployee> getEmployeeById(@PathVariable(name = "id", required = true) Integer id) {
        return success(employeeService.getEmployeeById(id));
    }

    @GetMapping(path = "/with-params")
    public List<DtoEmployee> getEmployeeWithParams(@RequestParam(name = "firstName", required = false) String firstName,
                                                   @RequestParam(name = "lastName", required = false) String lastName) {
        return employeeService.getEmployeeWithParams(firstName, lastName);
    }

    @PostMapping(path = "/save")
    public DtoEmployee saveEmployee(@RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.saveEmployee(dtoEmployeeIU);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteEmployee(@PathVariable(name = "id", required = true) Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "/update/{id}")
    public DtoEmployee updateEmployee(@PathVariable(name = "id") Integer id, @RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.updateEmployee(id, dtoEmployeeIU);
    }
}
