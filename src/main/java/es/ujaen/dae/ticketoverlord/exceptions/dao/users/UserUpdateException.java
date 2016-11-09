package es.ujaen.dae.ticketoverlord.exceptions.dao.users;

public class UserUpdateException extends RuntimeException {
    public UserUpdateException() {
        super();
    }

    public UserUpdateException(Exception e) {
        super(e);
    }
}
