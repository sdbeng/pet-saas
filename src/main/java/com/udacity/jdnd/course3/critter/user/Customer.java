package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;

    private List<Long> petIds;

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Long> petIds) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.petIds = petIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }
}
