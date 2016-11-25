package es.ujaen.dae.ticketoverlord.exceptions.dao.tickets;

public class TicketInsertionException extends RuntimeException {
    public TicketInsertionException() {
        super();
    }

    public TicketInsertionException(Exception e) {
        super(e);
    }
}
