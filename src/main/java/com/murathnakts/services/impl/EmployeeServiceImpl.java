package com.murathnakts.services.impl;

import com.murathnakts.dto.DtoAdress;
import com.murathnakts.dto.DtoDepartment;
import com.murathnakts.dto.DtoEmployee;
import com.murathnakts.dto.DtoEmployeeIU;
import com.murathnakts.entity.Employee;
import com.murathnakts.exception.BaseException;
import com.murathnakts.exception.ErrorMessage;
import com.murathnakts.exception.MessageType;
import com.murathnakts.repository.EmployeeRepository;
import com.murathnakts.services.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<DtoEmployee> getAllEmployeeList() {
        List<DtoEmployee> dtoEmployeeList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee : employeeList) {
            DtoEmployee dtoEmployee = new DtoEmployee();
            BeanUtils.copyProperties(employee, dtoEmployee);
            dtoEmployeeList.add(dtoEmployee);
        }
        return dtoEmployeeList;
    }

    @Override
    public DtoEmployee getEmployeeById(Integer id) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoAdress dtoAdress = new DtoAdress();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        BeanUtils.copyProperties(employee.get(), dtoEmployee);
        BeanUtils.copyProperties(employee.get().getAddress(), dtoAdress);
        BeanUtils.copyProperties(employee.get().getDepartment(), dtoDepartment);
        dtoEmployee.setAddress(dtoAdress);
        dtoEmployee.setDepartment(dtoDepartment);
        return dtoEmployee;
    }

    @Override
    public List<DtoEmployee> getEmployeeWithParams(String firstName, String lastName) {
        List<DtoEmployee> dtoEmployeeList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        for (Employee employee : employeeList) {
            DtoEmployee dtoEmployee = new DtoEmployee();
            BeanUtils.copyProperties(employee, dtoEmployee);
            dtoEmployeeList.add(dtoEmployee);
        }
        return dtoEmployeeList;
    }

    @Override
    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        Employee employee = new Employee();
        BeanUtils.copyProperties(dtoEmployeeIU, employee);
        Employee savedEmployee = employeeRepository.save(employee);
        BeanUtils.copyProperties(savedEmployee, dtoEmployee);
        return dtoEmployee;
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> findEmployee = employeeRepository.findById(id);
        findEmployee.ifPresent(employee -> employeeRepository.delete(employee));
    }

    @Override
    public DtoEmployee updateEmployee(Integer id, DtoEmployeeIU dtoEmployeeIU) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        Optional<Employee> findEmployee = employeeRepository.findById(id);
        if (findEmployee.isPresent()) {
            Employee employee = findEmployee.get();
            employee.setFirstName(dtoEmployeeIU.getFirstName());
            employee.setLastName(dtoEmployeeIU.getLastName());
            employee.setEmail(dtoEmployeeIU.getEmail());
            employee.setPassword(dtoEmployeeIU.getPassword());
            Employee updatedEmployee = employeeRepository.save(employee);
            BeanUtils.copyProperties(updatedEmployee, dtoEmployee);
        }
        return dtoEmployee;
    }
}
