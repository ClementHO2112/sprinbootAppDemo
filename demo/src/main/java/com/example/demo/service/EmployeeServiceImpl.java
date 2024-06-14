package com.example.demo.service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee emp) {
        employeeRepository.save(emp);
    }

    @Override
    public void delete(int empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        emp.ifPresent(employeeRepository::delete);
    }

    @Override
    public Employee findById(int empId) {
        Optional<Employee> emp = employeeRepository.findById(empId);

        return emp.orElseGet(Employee::new);

    }
}
