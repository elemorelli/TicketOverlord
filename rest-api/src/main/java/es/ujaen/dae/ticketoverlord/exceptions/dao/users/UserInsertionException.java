package es.ujaen.dae.ticketoverlord.exceptions.dao.users;

public class UserInsertionException extends RuntimeException {
    public UserInsertionException() {
        super();
    }

    public UserInsertionException(Exception e) {
        super(e);
    }
}
