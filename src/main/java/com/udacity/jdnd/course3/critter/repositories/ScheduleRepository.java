package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findPetsById(Long id);
    List<Schedule> findEmployeeById(Employee employeeList);
    List<Schedule> findByPetList(Pet petList);
    List<Schedule> findByEmployeeList(Employee employeeList);
    List<Schedule> findAllByPetListIn(List<Pet> pets);

}
