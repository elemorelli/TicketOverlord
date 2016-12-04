package es.ujaen.dae.ticketoverlord.exceptions.services.events;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No event found")
public class NoEventFoundException extends RuntimeException {
    public NoEventFoundException() {
        super();
    }

    public NoEventFoundException(String message) {
        super(message);
    }
}
