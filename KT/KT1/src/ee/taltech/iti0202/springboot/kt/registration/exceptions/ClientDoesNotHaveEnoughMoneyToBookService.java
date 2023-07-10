package ee.taltech.iti0202.springboot.kt.registration.exceptions;

public class ClientDoesNotHaveEnoughMoneyToBookService extends Exception {
    public static final String MESSAGE = "Client does not have enough money to book this service!";

    /**
     * constructor
     */
    public ClientDoesNotHaveEnoughMoneyToBookService() {
        super(MESSAGE);
    }
}
