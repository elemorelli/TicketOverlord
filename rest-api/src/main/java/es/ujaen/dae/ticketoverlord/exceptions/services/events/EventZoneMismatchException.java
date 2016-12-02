package es.ujaen.dae.ticketoverlord.exceptions.services.events;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EventZoneMismatchException extends RuntimeException {
    public EventZoneMismatchException() {
        super();
    }

    public EventZoneMismatchException(String message) {
        super(message);
    }
}
