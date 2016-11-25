package es.ujaen.dae.ticketoverlord.exceptions;

public class NoTicketsAvailableException extends Exception {
    public NoTicketsAvailableException() {
        super();
    }

    public NoTicketsAvailableException(Exception e) {
        super(e);
    }
}
