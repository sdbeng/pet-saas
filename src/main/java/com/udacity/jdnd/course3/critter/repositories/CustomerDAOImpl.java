package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    private CustomerRepository customerRepository;

    public CustomerDAOImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
