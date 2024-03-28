package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repositories.EmployeeDAO;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        return employeeDAO.getOne(employeeId);
    }
}
