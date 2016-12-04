package es.ujaen.dae.ticketoverlord.exceptions.services.venues;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No venue found")
public class NoVenueFoundException extends RuntimeException {
    public NoVenueFoundException() {
        super();
    }

    public NoVenueFoundException(String message) {
        super(message);
    }
}
