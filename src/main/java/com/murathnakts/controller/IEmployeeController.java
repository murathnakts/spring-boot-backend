package com.murathnakts.controller;

import com.murathnakts.dto.DtoEmployee;
import com.murathnakts.dto.DtoEmployeeIU;
import com.murathnakts.entity.RootEntity;

import java.util.List;

public interface IEmployeeController {


    public RootEntity<List<DtoEmployee>> getAllEmployeeList();

    public RootEntity<DtoEmployee> getEmployeeById(Integer id);

    public List<DtoEmployee> getEmployeeWithParams(String firstName, String lastName);

    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU);

    public void deleteEmployee(Integer id);

    public DtoEmployee updateEmployee(Integer id, DtoEmployeeIU dtoEmployeeIU);
}
