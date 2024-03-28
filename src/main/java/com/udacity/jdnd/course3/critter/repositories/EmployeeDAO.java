package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Employee;

public interface EmployeeDAO {
    Employee save(Employee employee);

    Employee getOne(long employeeId);
}
