package com.murathnakts.services;

import com.murathnakts.dto.DtoEmployee;
import com.murathnakts.dto.DtoEmployeeIU;

import java.util.List;

public interface IEmployeeService {
    public List<DtoEmployee> getAllEmployeeList();

    public DtoEmployee getEmployeeById(Integer id);

    public List<DtoEmployee> getEmployeeWithParams(String firstName, String lastName);

    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU);

    public void deleteEmployee(Integer id);

    public DtoEmployee updateEmployee(Integer id, DtoEmployeeIU dtoEmployeeIU);
}
