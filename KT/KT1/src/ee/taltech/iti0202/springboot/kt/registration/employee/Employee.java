package ee.taltech.iti0202.springboot.kt.registration.employee;

import ee.taltech.iti0202.springboot.kt.registration.service.ServiceType;

import java.util.List;

public class Employee {
    private String name;
    private String lastName;
    private List<ServiceType> types;

    /**
     * @param name     name
     * @param lastName lastName
     * @param types    types
     */
    public Employee(String name, String lastName, List<ServiceType> types) {
        this.name = name;
        this.lastName = lastName;
        this.types = types;
    }

    public String getName() {
        return name;
    }


    public String getLastName() {
        return lastName;
    }


    public List<ServiceType> getTypes() {
        return types;
    }

}
