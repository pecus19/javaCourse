package ee.taltech.iti0202.kt.registration.exceptions;

public class UnBookedService extends  Exception{
    public static final String MESSAGE = "This service is not booked!";

    /**
     * constructor
     */
    public UnBookedService() {
        super(MESSAGE);
    }
}
