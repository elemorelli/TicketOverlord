package es.ujaen.dae.ticketoverlord.exceptions;

public class UnauthorizedAccessException extends Exception {
    public UnauthorizedAccessException() {
        super();
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
