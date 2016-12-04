package es.ujaen.dae.ticketoverlord.exceptions.services.tickets;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No ticket found")
public class NoTicketFoundException extends RuntimeException {
    public NoTicketFoundException() {
        super();
    }

    public NoTicketFoundException(String message) {
        super(message);
    }
}
