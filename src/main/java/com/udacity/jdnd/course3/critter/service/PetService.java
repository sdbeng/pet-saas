package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDAO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetDAO PetDAO;
    public Pet savePet(Pet pet) {
        return PetDAO.save(pet);
    }

    public Pet getPet(long petId) {
        return PetDAO.getOne(petId);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return PetDAO.getPetsByOwner(ownerId);
    }

}
