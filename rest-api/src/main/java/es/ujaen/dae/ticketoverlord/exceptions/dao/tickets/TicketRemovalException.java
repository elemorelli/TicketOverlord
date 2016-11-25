package es.ujaen.dae.ticketoverlord.exceptions.dao.tickets;

public class TicketRemovalException extends RuntimeException {
    public TicketRemovalException() {
        super();
    }

    public TicketRemovalException(Exception e) {
        super(e);
    }
}
