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
        Pet thePet = petService.savePet(convertPetDTOToPet(petDTO));
        return convertPetToPetDTO(thePet);
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
            System.out.println("***** getPetFromDTO customer: " + customer);
            if (customer != null) {
                pet.setOwner(customer);
                customer.getPets().add(pet);
            }
        }
        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet savedPet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(savedPet, petDTO);
        petDTO.setOwnerId(savedPet.getOwner().getId());
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = new Customer();
        customer.setId(petDTO.getOwnerId());
        pet.setCustomer(customer);
        return pet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return convertPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOList = new ArrayList<>();
        List<Pet> petsList = petService.getPets();
        petsList.forEach(pet -> petDTOList.add(convertPetToPetDTO(pet)));
        return petDTOList;

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOs = new ArrayList<>();
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        pets.forEach(pet -> petDTOs.add(convertPetToPetDTO(pet)));
        System.out.println("***** petDTOs: " + petDTOs);
        return petDTOs;
    }

}
