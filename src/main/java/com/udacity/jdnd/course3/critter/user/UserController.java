package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private CustomerService customerService;
    private EmployeeService employeeService;

    private PetService petService;

    public UserController(CustomerService customerService, PetService petService, EmployeeService employeeService) {
        this.petService = petService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

//    @Autowired
//    public void setEmployeeService(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertCustomerToCustomerDTO(savedCustomer);
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    //convert Customer object to CustomerDTO object
    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = convertCustomerListToCustomerDTOList(customers);
        //before returning, make sure to populate the petIds field of each CustomerDTO
        for (CustomerDTO customerDTO : customerDTOS) {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : customerDTO.getPets()) {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        }
        return customerDTOS;
    }

    private List<CustomerDTO> convertCustomerListToCustomerDTOList(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = convertCustomerToCustomerDTO(customer);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    //asign Pet to customer - will refactor the savePet method in PetCtrl instead
//    @PutMapping("/customer/pets/{petId}")
//    public CustomerDTO addPetToCustomer(@PathVariable long petId, @RequestBody CustomerDTO customerDTO){
//        Pet petAssigned = petService.getPet(petId);
//        System.out.println("petAssigned: " + petAssigned);
//        return convertCustomerToCustomerDTO(customerService.addPetToCustomer(petAssigned, customerDTO.getId()));
//
//    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return convertEmployeeToEmployeeDTO(savedEmployee);
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee savedEmployee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(savedEmployee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
