package ee.taltech.iti0202.computerbuilder.exceptions;

public class DifferentComputerTypeException extends Exception{
    public static final String MESSAGE = "You choose components for a different computer type!";

    /**
     * constructor
     */
    public DifferentComputerTypeException() {
        super(MESSAGE);
    }
}
