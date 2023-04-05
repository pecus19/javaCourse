package ee.taltech.iti0202.kt.registration.exceptions;

public class NotTheCorrectEmployeeForTheService extends Exception {
    public static final String MESSAGE = "This employee can not do this service!";

    /**
     * constructor
     */
    public NotTheCorrectEmployeeForTheService() {
        super(MESSAGE);
    }
}
