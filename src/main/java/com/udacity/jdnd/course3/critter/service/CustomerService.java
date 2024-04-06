package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
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


    public void addPetToCustomer(Pet petAssigned, Customer customer) {
        List<Pet> petList = customer.getPets();
        if (petList == null) {
            petList = new ArrayList<>();
        }
        petList.add(petAssigned);
        customer.setPets(petList);
        customerRepository.save(customer);
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
