package ee.taltech.iti0202.kt.registration.beautySalon;

import ee.taltech.iti0202.kt.registration.client.Client;
import ee.taltech.iti0202.kt.registration.exceptions.BookedService;
import ee.taltech.iti0202.kt.registration.exceptions.NotTheCorrectEmployeeForTheService;
import ee.taltech.iti0202.kt.registration.exceptions.ThisIsVIPService;
import ee.taltech.iti0202.kt.registration.exceptions.UnBookedService;
import ee.taltech.iti0202.kt.registration.service.Service;
import ee.taltech.iti0202.kt.registration.service.ServiceType;
import ee.taltech.iti0202.kt.registration.employee.Employee;
import ee.taltech.iti0202.kt.registration.service.Significance;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BeautySalon {
    Logger logger = Logger.getLogger(BeautySalon.class.getName());
    private String name;
    private List<ServiceType> types;
    private List<Employee> employeeList = new ArrayList<>();
    private List<Service> bookedServiceList = new ArrayList<>();
    private List<Service> unBookedServiceList = new ArrayList<>();

    public BeautySalon(String name, List<ServiceType> types) {
        this.name = name;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public Service serviceBooking(Service service, Client client) throws NotTheCorrectEmployeeForTheService,
            BookedService {
        if (!client.isEnoughMoney(service)) {
            throw new NotTheCorrectEmployeeForTheService();
        }
        if (service.isBooked()) {
            throw new BookedService();
        }
        service.setBooked(true);
        removeBookedServiceFromUnBooked(service);
        client.buyService(service);
        return service;
    }

    public void cancelServiceForClient(Service service, Client client) throws UnBookedService {
        if (!service.isBooked()) {
            throw new UnBookedService();
        }
        service.setBooked(false);
        bookedServiceList.remove(service);
        unBookedServiceList.add(service);
        client.setMoney(client.getMoney() + service.getPrice());
        logger.info(String.format("The service %s has been canceled by Client with name %s",
                service.getName(), client.getName()));
    }

    public void cancelServiceForEmployee(Employee employee, Service service, Client client) throws UnBookedService,
            ThisIsVIPService {
        if (!service.isBooked()) {
            throw new UnBookedService();
        }
        if (service.getSignificance() == Significance.VIP) {
            throw new ThisIsVIPService();
        }
        service.setBooked(false);
        bookedServiceList.remove(service);
        unBookedServiceList.add(service);
        client.setMoney(client.getMoney() + service.getPrice());
        logger.info(String.format("The service %s has been canceled by Employee with name %s",
                service.getName(), employee.getName()));
    }

    public void removeBookedServiceFromUnBooked(Service service) {
        if (unBookedServiceList.contains(service) && service.isBooked()) {
            bookedServiceList.add(service);
            unBookedServiceList.remove(service);
            logger.info(String.format("The service %s has been removed from unbooked", service.getName()));
        }
    }

    public Service findServiceByType(ServiceType type) {
        List<Service> result = new ArrayList<>();
        for (int i = 0; i < unBookedServiceList.size(); i++) {
            if (unBookedServiceList.get(i).getType().equals(type)) {
                int min = unBookedServiceList.get(0).getPrice();
                for (int j = 1; j < unBookedServiceList.size(); j++) {
                    int value = unBookedServiceList.get(j).getPrice();
                    if (value < min) {
                        min = value;
                    }
                }
                for (Service value : unBookedServiceList) {
                    if (value.getPrice() == min) {
                        result.add(value);
                    }
                }

            }
        }
        return result.get(0);
    }


    public void addService(Service service) throws NotTheCorrectEmployeeForTheService, BookedService {
        if (!unBookedServiceList.contains(service)) {
            if (!service.isBooked()) {
                if (service.getEmployee().getTypes().contains(service.getType())) {
                    unBookedServiceList.add(service);
                    logger.info(String.format("The service %s, employee %s %s was added to the services of the %s",
                            service.getName(), service.getEmployee().getName(), service.getEmployee().getLastName(),
                            this.getName()));
                } else {
                    throw new NotTheCorrectEmployeeForTheService();
                }
            }
        } else {
            throw new BookedService();
        }
    }

    public void addEmployee(Employee employee) {
        if (!employeeList.contains(employee)) {
            employeeList.add(employee);
        }
    }
}
