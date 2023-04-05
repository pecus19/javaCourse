package ee.taltech.iti0202.kt.registration.employee;

import ee.taltech.iti0202.kt.registration.service.ServiceType;

import java.util.List;

public class Employee {
    private String name;
    private String lastName;
    private List<ServiceType> types;


    public Employee(String name, String lastName, List<ServiceType> types) {
        this.name = name;
        this.lastName = lastName;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ServiceType> getTypes() {
        return types;
    }

    public void setTypes(List<ServiceType> types) {
        this.types = types;
    }

}
