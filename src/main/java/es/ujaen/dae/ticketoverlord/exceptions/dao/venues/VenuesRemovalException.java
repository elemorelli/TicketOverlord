package es.ujaen.dae.ticketoverlord.exceptions.dao.venues;

public class VenuesRemovalException extends RuntimeException {
    public VenuesRemovalException() {
        super();
    }

    public VenuesRemovalException(Exception e) {
        super(e);
    }
}
