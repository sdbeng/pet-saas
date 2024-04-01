package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer getOne(long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public Optional<Customer> findById(long ownerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(ownerId);
        if(optionalCustomer.isPresent()){
            //return optionalCustomer
            return optionalCustomer;
    }
         else {
            return Optional.empty();
        }
    }
}
