package es.ujaen.dae.ticketoverlord.exceptions.services.tickets;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NoTicketsAvailableException extends RuntimeException {
    public NoTicketsAvailableException() {
        super();
    }

    public NoTicketsAvailableException(Exception e) {
        super(e);
    }
}
