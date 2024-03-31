package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer getOne(long id);

    Optional<Customer> findById(long ownerId);
}
