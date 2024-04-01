package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerDAO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer addPetToCustomer(Pet petAssigned, long id) {
        Customer customer = customerRepository.getOne(id);
        customer.addPet(petAssigned);
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(long ownerId) {
        Optional<Customer> customer = customerRepository.findById(ownerId);
        if(customer.isPresent()) {
            return customer;
        } else {
//            throw new NullPointerException("Customer not found");
            return Optional.empty();
        }
    }

    public Customer getCustomer(Long ownerId) {
        return customerRepository.getOne(ownerId);
    }
}
