package ee.taltech.iti0202.bakery.customer;

import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class CustomerTest {
    @Test
    protected void createACustomerWithWrongBankAccountTest() {
        try {
            new CustomerBuilder().setName("Danila")
                    .setAge(21)
                    .setBankAccount((double) -23)
                    .createCustomer();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Bank account must be a positive number.", ex.getMessage());
        }
    }

    @Test
    protected void createACustomerWithWrongNameTest() {
        try {
            new CustomerBuilder().setName(null)
                    .setAge(21)
                    .setBankAccount((double) 0)
                    .createCustomer();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be empty", ex.getMessage());
        }
    }

    @Test
    protected void createACustomerWithWrongAgeTest() {
        try {
            new CustomerBuilder().setName("Dan")
                    .setAge(-1)
                    .setBankAccount((double) 12)
                    .createCustomer();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Age must be a positive number.", ex.getMessage());
        }
    }


}
