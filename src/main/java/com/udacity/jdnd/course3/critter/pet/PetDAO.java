package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import com.udacity.jdnd.course3.critter.pet.PetType;

public interface PetDAO {
    Pet save(Pet pet);

    Pet getOne(long petId);

    List<Pet> getPetsByOwner(long ownerId);
}
