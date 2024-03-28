package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repositories.CustomerDAO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer saveCustomer(Customer customer) {
        return customerDAO.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }
}
