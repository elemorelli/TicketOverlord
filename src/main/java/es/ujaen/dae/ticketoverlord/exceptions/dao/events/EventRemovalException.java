package es.ujaen.dae.ticketoverlord.exceptions.dao.events;

public class EventRemovalException extends RuntimeException {
    public EventRemovalException() {
        super();
    }

    public EventRemovalException(Exception e) {
        super(e);
    }
}
