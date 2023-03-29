package io.spring.api.services;

import java.util.List;

import io.spring.api.models.Employee;

public interface EmployeeService{
    public List<Employee> Get();
    public Employee Get(Integer id);
    public Boolean save(Employee employee);
    public Boolean delete(Integer id);
}
