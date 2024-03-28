package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer save(Customer customer);

    List<Customer> findAll();
}
