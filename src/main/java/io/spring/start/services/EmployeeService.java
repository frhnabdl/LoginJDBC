package io.spring.start.services;

import java.util.List;

import io.spring.start.models.Employee;

public interface EmployeeService {
    public List<Employee> Get(); //READ -> method get all / select *
    public Employee Get(Integer id); //READ -> method get by id / select where id -> untuk UPDATE
    public Boolean Save(Employee employee); //CREATE -> method insert data
    public Boolean Delete(Integer id); //DELETE -> method delete row
}
