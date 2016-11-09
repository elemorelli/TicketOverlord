package es.ujaen.dae.ticketoverlord.exceptions.dao.users;

public class UserRemovalException extends RuntimeException {
    public UserRemovalException() {
        super();
    }

    public UserRemovalException(Exception e) {
        super(e);
    }
}
