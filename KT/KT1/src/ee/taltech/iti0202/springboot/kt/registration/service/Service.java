package ee.taltech.iti0202.springboot.kt.registration.service;

import ee.taltech.iti0202.springboot.kt.registration.employee.Employee;

import java.time.LocalDateTime;

public class Service {
    private String name;
    private LocalDateTime startTime;
    private ServiceType type;
    private double duration;
    private Employee employee;
    private Integer price;
    private Significance significance;
    private boolean isBooked = false;

    /**
     * @param name         name
     * @param type         type
     * @param startTime    startTime
     * @param duration     duration
     * @param employee     employee
     * @param price        price
     * @param significance significance
     */
    public Service(String name, ServiceType type, LocalDateTime startTime, double duration,
                   Employee employee, Integer price, Significance significance) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.employee = employee;
        this.price = price;
        this.significance = significance;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }


    public ServiceType getType() {
        return type;
    }


    public Employee getEmployee() {
        return employee;
    }

    public Integer getPrice() {
        return price;
    }


    public String getName() {
        return name;
    }


    public Significance getSignificance() {
        return significance;
    }

}
