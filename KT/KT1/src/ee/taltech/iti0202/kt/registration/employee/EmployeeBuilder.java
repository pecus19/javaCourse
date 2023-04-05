package ee.taltech.iti0202.kt.registration.employee;

import ee.taltech.iti0202.kt.registration.service.ServiceType;

import java.util.List;

public class EmployeeBuilder {
    private String name;
    private String lastName;
    private List<ServiceType> types;

    /**
     * @param name name
     * @return this
     */
    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param lastName lastName
     * @return this
     */
    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * @param types types
     * @return this
     */
    public EmployeeBuilder setTypes(List<ServiceType> types) {
        this.types = types;
        return this;
    }

    /**
     * @return Employee
     */
    public Employee createEmployee() {
        return new Employee(name, lastName, types);
    }
}
