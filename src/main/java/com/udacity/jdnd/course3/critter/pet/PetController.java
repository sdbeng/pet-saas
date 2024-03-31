package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    private CustomerService customerService;


    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = getPetFromDTO(petDTO);
        pet = petService.savePet(pet);
        petDTO.setId(pet.getId());
        return petDTO;

//        Pet savedPet = petService.savePet(pet);
//        return convertPetToPetDTO(savedPet);
    }

    private Pet getPetFromDTO(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        if(Objects.nonNull(petDTO.getOwnerId())) {
            Optional<Customer> customerOptional = customerService.findById(petDTO.getOwnerId());
            Customer customer = customerOptional.orElse(null);
            if (customer != null) {
                pet.setCustomer(customer);
                customer.getPets().add(pet);
            }
        }
        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet savedPet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(savedPet, petDTO);
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        System.out.println("***** ownerId: " + ownerId);
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        System.out.println("***** pets: " + pets);
        return convertPetListToPetDTOList(pets);
    }

    private List<PetDTO> convertPetListToPetDTOList(List<Pet> pets) {
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet pet : pets) {
            PetDTO petDTO = convertPetToPetDTO(pet);
            petDTOList.add(petDTO);
        }
        return petDTOList;
    }
}
