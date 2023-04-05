package ee.taltech.iti0202.kt.registration.client;

import ee.taltech.iti0202.kt.registration.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Integer money;
    private List<Service> serviceList = new ArrayList<>();

    public Client(String name, Integer price) {
        this.name = name;
        this.money = price;
    }

    public String getName() {
        return name;
    }


    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public boolean isEnoughMoney(Service service) {
        return (getMoney() - service.getPrice()) >= 0;
    }

    public void buyService(Service service) {
        setMoney(getMoney() - service.getPrice());
        serviceList.add(service);
    }

    public List<Service> getServiceList() {
        return serviceList;
    }
}
