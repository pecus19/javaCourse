package ee.taltech.iti0202.springboot.kt.registration.service;

import ee.taltech.iti0202.springboot.kt.registration.employee.Employee;

import java.time.LocalDateTime;

public class ServiceBuilder {
    private String name;
    private ServiceType type;
    private LocalDateTime startTime;
    private double duration;
    private Employee employee;
    private Integer price;
    private Significance significance;

    /**
     * @param name name
     * @return this
     */
    public ServiceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param type type
     * @return this
     */
    public ServiceBuilder setType(ServiceType type) {
        this.type = type;
        return this;
    }

    /**
     * @param startTime startTime
     * @return this
     */
    public ServiceBuilder setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @param duration duration
     * @return this
     */
    public ServiceBuilder setDuration(double duration) {
        this.duration = duration;
        return this;
    }

    /**
     * @param employee employee
     * @return this
     */
    public ServiceBuilder setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    /**
     * @param price price
     * @return this
     */
    public ServiceBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * @param significance significance
     * @return this
     */
    public ServiceBuilder setSignificance(Significance significance) {
        this.significance = significance;
        return this;
    }

    /**
     * @return Service
     */
    public Service createService() {
        return new Service(name, type, startTime, duration, employee, price, significance);
    }
}
