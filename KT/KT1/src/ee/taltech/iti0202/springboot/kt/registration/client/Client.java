package ee.taltech.iti0202.springboot.kt.registration.client;

import ee.taltech.iti0202.springboot.kt.registration.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Integer money;
    private List<Service> serviceList = new ArrayList<>();

    /**
     * @param name  name
     * @param price price
     */
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

    /**
     * @param service service
     * @return this
     */
    public boolean isEnoughMoney(Service service) {
        return (getMoney() - service.getPrice()) >= 0;
    }

    /**
     * @param service service
     */
    public void buyService(Service service) {
        setMoney(getMoney() - service.getPrice());
        serviceList.add(service);
    }

    /**
     * @return serviceList
     */
    public List<Service> getServiceList() {
        return serviceList;
    }
}
