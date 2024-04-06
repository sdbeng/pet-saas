package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private EmployeeRepository employeeRepository;

    private PetRepository petRepository;

    private CustomerRepository customerRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }
    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);

        List<Pet> pets = petRepository.findAllById(petIds);

        schedule.setEmployeeList(employees);
        schedule.setPetList(pets);
        if(schedule == null) {
            schedule = new Schedule();
//            throw new NullPointerException("Schedule is null");
        }
        return scheduleRepository.save(schedule);

    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        List<Schedule> scheduleList = scheduleRepository.findByEmployeeList(employeeRepository.getOne(employeeId));
        return scheduleList;
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepository.findByPetList(petRepository.getOne(petId));
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepository.findAllByPetListIn(customerRepository.getOne(customerId).getPets());
    }
}
