package es.ujaen.dae.ticketoverlord.exceptions;

public class UserCreationException extends RuntimeException {
    public UserCreationException(Exception e) {
        super(e);
    }
}
