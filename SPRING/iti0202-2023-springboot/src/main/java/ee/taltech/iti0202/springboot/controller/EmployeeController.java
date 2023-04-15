package ee.taltech.iti0202.springboot.controller;

import ee.taltech.iti0202.springboot.repository.Employee;
import ee.taltech.iti0202.springboot.repository.EmployeeRepository;
import ee.taltech.iti0202.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    /**
     * @param id
     * @return Employee
     */
    @GetMapping(value = "/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") long id) {
        return Optional.ofNullable(employeeService.findEmployeeById(id));

    }

    /**
     * @param employee employee
     * @return String
     */
    @PostMapping("/employee/add")
    public String addEmployee(@RequestBody Employee employee) {
        if (employeeRepository.findByEmailIgnoreCase(
                employee.getEmail()).isPresent()) {
            return "This email is already in" +
                    " database or something went wrong!";
        } else {
            employeeService.addEmployee(employee);
            return "Employee added to database";
        }
    }

    /**
     * @param id id
     * @return String
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            employeeService.deleteEmployee(employee);
            return "Employee deleted";
        } else {
            return "No matching ID found in database!";
        }
    }

    /**
     * @param id       id
     * @param employee employee
     * @return String
     */
    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        final Employee existingEmployee = employeeService.findEmployeeById(id);
        if (existingEmployee == null) {
            return "Cannot overwrite employee data!";
        }
        String newEmail = employee.getEmail();
        if (!existingEmployee.getEmail().equals(newEmail)) {
            Optional<Employee> employeeWithEmail
                    = employeeRepository.findByEmailIgnoreCase(newEmail);
            if (employeeWithEmail.isPresent()) {
                return "Cannot overwrite employee data!";
            }
        }
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setCompany(employee.getCompany());

        employeeRepository.save(existingEmployee);
        return "Employee data overwritten..";
    }

    /**
     * @param company company
     * @return List
     */
    @GetMapping("/employee")
    public List<Employee> sortEmployee(@RequestParam String company) {
        return employeeService.findEmployeesByCompany(company);
    }
}
