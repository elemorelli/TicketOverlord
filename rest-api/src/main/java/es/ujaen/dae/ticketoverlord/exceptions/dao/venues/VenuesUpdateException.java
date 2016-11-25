package es.ujaen.dae.ticketoverlord.exceptions.dao.venues;

public class VenuesUpdateException extends RuntimeException {
    public VenuesUpdateException() {
        super();
    }

    public VenuesUpdateException(Exception e) {
        super(e);
    }
}
