package ee.taltech.iti0202.springboot.input;

public class FileReaderException extends RuntimeException {
    /**
     * @param message smt.
     * @param e       smt.
     */
    public FileReaderException(String message, Throwable e) {
        super(message, e);
    }
}
