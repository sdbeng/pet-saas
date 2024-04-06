package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;
    private CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        customerService.addPetToCustomer(savedPet, savedPet.getCustomer());
        return savedPet;
    }

    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getPetsByOwner(Long customerId) {
        return petRepository.findPetsByCustomerId(customerId);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }
}
