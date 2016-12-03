package es.ujaen.dae.ticketoverlord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TicketTransactionException extends RuntimeException {
    public TicketTransactionException() {
        super();
    }

    public TicketTransactionException(Exception e) {
        super(e);
    }
}
