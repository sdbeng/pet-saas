package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDAOImpl implements PetDAO {
    private PetRepository petRepository;


    public PetDAOImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet getOne(long petId) {
        return petRepository.getOne(petId);
    }

    @Override
    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
