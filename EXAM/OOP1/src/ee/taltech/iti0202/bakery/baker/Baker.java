package ee.taltech.iti0202.bakery.baker;

import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.product.Product;
import java.util.List;

public class Baker extends Customer {
    private List<Product.Types> types;

    public List<Product.Types> getTypes() {
        return types;
    }
    private boolean isWorks = false;

    public Baker(String name, int age, Double bankAccount, List<Product.Types> types) {
        super(name, age, bankAccount);
        this.types = types;
    }

    public boolean isWorks() {
        return isWorks;
    }

    public void setWorks(boolean works) {
        isWorks = works;
    }
}
