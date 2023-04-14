package ee.taltech.iti0202.springboot.service;

import ee.taltech.iti0202.springboot.repository.Employee;
import ee.taltech.iti0202.springboot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // loob konstuktori ise parameetriga EmployeeRepository
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public List<Employee> findEmployeesByCompany(String company) {
        return employeeRepository.findEmployeeByCompany(company);
    }
}
