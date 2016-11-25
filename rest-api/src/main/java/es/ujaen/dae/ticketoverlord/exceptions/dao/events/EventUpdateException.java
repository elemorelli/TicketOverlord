package es.ujaen.dae.ticketoverlord.exceptions.dao.events;

public class EventUpdateException extends RuntimeException {
    public EventUpdateException() {
        super();
    }

    public EventUpdateException(Exception e) {
        super(e);
    }
}
