package io.spring.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.spring.api.models.Employee;
import io.spring.api.repositories.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> Get() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee Get(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data Department Tidak Ditemukan"));
    }

    @Override
    public Boolean save(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
       employeeRepository.deleteById(id);
       return employeeRepository.findById(id).isPresent();
    }
    
}
