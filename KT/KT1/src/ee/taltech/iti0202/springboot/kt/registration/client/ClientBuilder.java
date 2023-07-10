package ee.taltech.iti0202.springboot.kt.registration.client;

public class ClientBuilder {
    private String name;
    private Integer price;

    /**
     * @param name name
     * @return this
     */
    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param price price
     * @return this
     */
    public ClientBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * @return this
     */
    public Client createClient() {
        return new Client(name, price);
    }
}
