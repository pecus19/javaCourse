package ee.taltech.iti0202.kt.registration.beautySalon;

import ee.taltech.iti0202.kt.registration.client.Client;
import ee.taltech.iti0202.kt.registration.client.ClientBuilder;
import ee.taltech.iti0202.kt.registration.employee.Employee;
import ee.taltech.iti0202.kt.registration.employee.EmployeeBuilder;
import ee.taltech.iti0202.kt.registration.exceptions.BookedService;
import ee.taltech.iti0202.kt.registration.exceptions.NotTheCorrectEmployeeForTheService;
import ee.taltech.iti0202.kt.registration.exceptions.ThisIsVIPService;
import ee.taltech.iti0202.kt.registration.exceptions.UnBookedService;
import ee.taltech.iti0202.kt.registration.service.Service;
import ee.taltech.iti0202.kt.registration.service.ServiceBuilder;
import ee.taltech.iti0202.kt.registration.service.ServiceType;
import ee.taltech.iti0202.kt.registration.service.Significance;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeautySalonTest {

    @org.junit.jupiter.api.Test
    void clientCanBuyAServiceTest() throws BookedService, NotTheCorrectEmployeeForTheService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Client client = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        assertEquals(service, beautySalon.serviceBooking(service, client));
    }

    @org.junit.jupiter.api.Test
    void clientCanNotBuyAServiceTest() throws BookedService, NotTheCorrectEmployeeForTheService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Client client = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        Client client2 = new ClientBuilder()
                .setName("Danila")
                .setPrice(100)
                .createClient();
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        try {
            beautySalon.serviceBooking(service, client2);
        } catch (BookedService ex) {
            assertEquals("This service has already been booked!", ex.getMessage());
        }
    }
    @org.junit.jupiter.api.Test
    void employeeHasADifferentTypeTest() throws BookedService, NotTheCorrectEmployeeForTheService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Client client = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.Massage)
                .createService();
        beautySalon.addEmployee(employee);
        try {
            beautySalon.addService(service);
        } catch (NotTheCorrectEmployeeForTheService ex) {
            assertEquals("This employee can not do this service!", ex.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void checkClientsMoneyTest() throws BookedService, NotTheCorrectEmployeeForTheService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Client client = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        assertEquals(50, client.getMoney());
    }

    @org.junit.jupiter.api.Test
    void findServiceByTypeTest() throws BookedService, NotTheCorrectEmployeeForTheService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(40)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        Service service2 = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        Service service3 = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(30)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        beautySalon.addService(service);
        beautySalon.addService(service2);
        beautySalon.addService(service3);
        beautySalon.addEmployee(employee);
        assertEquals(service3, beautySalon.findServiceByType(ServiceType.HairColoring));
    }
    @org.junit.jupiter.api.Test
    void cancelServesAndBookByAnotherClientTest() throws BookedService, NotTheCorrectEmployeeForTheService,
            UnBookedService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(40)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        Client client = new ClientBuilder()
                .setName("Ivan")
                .setPrice(100)
                .createClient();
        Client client2 = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        beautySalon.cancelServiceForClient(service, client);
        assertEquals(service, beautySalon.serviceBooking(service, client2));
    }
    @org.junit.jupiter.api.Test
    void cancelServesByEmployeeTest() throws BookedService, NotTheCorrectEmployeeForTheService, UnBookedService,
            ThisIsVIPService {
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(40)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.Regular)
                .setType(ServiceType.HairColoring)
                .createService();
        Client client = new ClientBuilder()
                .setName("Ivan")
                .setPrice(100)
                .createClient();
        Client client2 = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        beautySalon.cancelServiceForEmployee(employee, service, client);
        assertEquals(service, beautySalon.serviceBooking(service, client2));
    }
    @org.junit.jupiter.api.Test
    void cancelServesByEmployeeVIPTest() throws BookedService, NotTheCorrectEmployeeForTheService, UnBookedService{
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(40)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.VIP)
                .setType(ServiceType.HairColoring)
                .createService();
        Client client = new ClientBuilder()
                .setName("Ivan")
                .setPrice(100)
                .createClient();
        Client client2 = new ClientBuilder()
                .setName("Katja")
                .setPrice(100)
                .createClient();
        beautySalon.addService(service);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        try {
            beautySalon.cancelServiceForEmployee(employee, service, client);
        } catch (ThisIsVIPService ex) {
            assertEquals("VIP service can not be canceled!", ex.getMessage());
        }
    }
    @org.junit.jupiter.api.Test
    void checkClientsList() throws BookedService, NotTheCorrectEmployeeForTheService, UnBookedService{
        BeautySalon beautySalon = new BeautySalon("Lilled", List.of(ServiceType.HairColoring,
                ServiceType.HairCutting, ServiceType.Massage, ServiceType.Makeup,
                ServiceType.Manicures, ServiceType.Pedicures));
        Employee employee = new EmployeeBuilder()
                .setName("Olga")
                .setLastName("Petrova")
                .setTypes(List.of(ServiceType.HairColoring, ServiceType.HairCutting))
                .createEmployee();
        Service service = new ServiceBuilder()
                .setName("Hair Coloring")
                .setEmployee(employee)
                .setPrice(40)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.VIP)
                .setType(ServiceType.HairColoring)
                .createService();
        Service service2 = new ServiceBuilder()
                .setName("Servis2")
                .setEmployee(employee)
                .setPrice(50)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.VIP)
                .setType(ServiceType.HairColoring)
                .createService();
        Service service3 = new ServiceBuilder()
                .setName("Servis3")
                .setEmployee(employee)
                .setPrice(10)
                .setStartTime(LocalDateTime.of(2017, 1, 14, 10, 34))
                .setDuration(1.5)
                .setSignificance(Significance.VIP)
                .setType(ServiceType.HairColoring)
                .createService();
        Client client = new ClientBuilder()
                .setName("Ivan")
                .setPrice(100)
                .createClient();
        beautySalon.addService(service);
        beautySalon.addService(service2);
        beautySalon.addService(service3);
        beautySalon.addEmployee(employee);
        beautySalon.serviceBooking(service, client);
        beautySalon.serviceBooking(service2, client);
        beautySalon.serviceBooking(service3, client);
        assertEquals(3, client.getServiceList().size());
    }
}
