package pl.sdacademy.java.jdbc.workshop4;

public class RentalException extends Exception {
    public RentalException(String message) {
        super(message);
    }

    public RentalException(Throwable cause) {
        super(cause);
    }
}
