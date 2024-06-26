package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds = new ArrayList<>();

    public long getId() {
        System.out.println("CustomerDTO.getId called===" + id);
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        System.out.println("CustomerDTO.getPetIds called===" + petIds);
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
//        if(petIds != null){
//            this.petIds = petIds;
//        }else{
//            this.petIds = new ArrayList<>();
//        }
    }

    public Pet[] getPets() {
        return petIds.stream().map(id -> {
            Pet pet = new Pet();
            pet.setId(id);
            return pet;
        }).toArray(Pet[]::new);
    }
}
