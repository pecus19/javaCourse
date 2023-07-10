package ee.taltech.iti0202.springboot.hello;

public class Apple extends Fruit {

    @Override
    public int getPrice() {
        return super.getPrice() * 3 + super.getPrice();
    }
}