package ee.taltech.iti0202.kt.registration.client;

import ee.taltech.iti0202.kt.registration.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Integer price;
    private List<Service> serviceList = new ArrayList<>();

    public Client(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isEnoughMoney(Service service) {
        return (getPrice() - service.getPrice()) >= 0;
    }

    public void buyService(Service service) {
        setPrice(getPrice() - service.getPrice());
        serviceList.add(service);
    }

    public List<Service> getServiceList() {
        return serviceList;
    }
}
