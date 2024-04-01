package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerDAO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Customer addPetToCustomer(Pet petAssigned, long id) {
        Customer customer = customerDAO.getOne(id);
        customer.addPet(petAssigned);
        return customerDAO.save(customer);
    }

    public Optional<Customer> findById(long ownerId) {
        Optional<Customer> customer = customerDAO.findById(ownerId);
        if(customer.isPresent()) {
            return customer;
        } else {
//            throw new NullPointerException("Customer not found");
            return Optional.empty();
        }
    }
}
