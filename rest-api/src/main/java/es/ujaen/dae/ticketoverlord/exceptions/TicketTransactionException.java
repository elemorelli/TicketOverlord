package es.ujaen.dae.ticketoverlord.exceptions;

public class TicketTransactionException extends Exception {
    public TicketTransactionException() {
        super();
    }

    public TicketTransactionException(Exception e) {
        super(e);
    }
}
