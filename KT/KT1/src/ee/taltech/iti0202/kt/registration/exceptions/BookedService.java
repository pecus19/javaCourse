package ee.taltech.iti0202.kt.registration.exceptions;

public class BookedService extends Exception {
    public static final String MESSAGE = "This service has already been booked!";

    /**
     * constructor
     */
    public BookedService() {
        super(MESSAGE);
    }
}
