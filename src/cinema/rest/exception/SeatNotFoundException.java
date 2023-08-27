package cinema.rest.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String message) {
        super(message);
    }

    public SeatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatNotFoundException(Throwable cause) {
        super(cause);
    }
}
