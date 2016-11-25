package es.ujaen.dae.ticketoverlord.exceptions.dao.events;

public class EventInsertionException extends RuntimeException {
    public EventInsertionException() {
        super();
    }

    public EventInsertionException(Exception e) {
        super(e);
    }
}
