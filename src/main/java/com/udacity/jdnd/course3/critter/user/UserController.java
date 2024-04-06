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

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertCustomerToCustomerDTO(savedCustomer);
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        if(customerDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            customerDTO.getPetIds().forEach(petId -> {
                Pet pet = new Pet();
                pet.setId(petId);
                pets.add(pet);});
            customer.setPets(pets);
            }
        return customer;
    }

    //convert Customer object to CustomerDTO object
    private static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if (customer.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            customer.getPets().forEach(pet -> petIds.add(pet.getId()));
            customerDTO.setPetIds(petIds);
        }
//        else{
//            System.out.println("Customer has no pets----------");
//            customerDTO.setPetIds(new ArrayList<>());
//        }
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerService.getAllCustomers();
        //before returning, make sure to populate the petIds field of each CustomerDTO
        customers.forEach(customer -> customerDTOS.add(convertCustomerToCustomerDTO(customer)));
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.getPet(petId);
        CustomerDTO customerDTO = convertCustomerToCustomerDTO(customerService.getCustomer(pet.getCustomer().getId()));
        return customerDTO;
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
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeRequest employeeRequest = convertEmployeeRequestDTOToEmployeeRequest(employeeDTO);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employeeList = employeeService.findEmployeesForService(employeeRequest);
        employeeList.forEach(employee -> employeeDTOS.add(convertEmployeeToEmployeeDTO(employee)));
        return employeeDTOS;
    }

    private EmployeeRequest convertEmployeeRequestDTOToEmployeeRequest(EmployeeRequestDTO employeeDTO) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        BeanUtils.copyProperties(employeeDTO, employeeRequest);
        return employeeRequest;
    }

}
