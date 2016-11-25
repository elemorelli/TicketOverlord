package es.ujaen.dae.ticketoverlord.exceptions.dao.tickets;

public class TicketUpdateException extends RuntimeException {
    public TicketUpdateException() {
        super();
    }

    public TicketUpdateException(Exception e) {
        super(e);
    }
}
