package es.ujaen.dae.ticketoverlord.exceptions.dao.venues;

public class VenuesInsertionException extends RuntimeException {
    public VenuesInsertionException() {
        super();
    }

    public VenuesInsertionException(Exception e) {
        super(e);
    }
}
