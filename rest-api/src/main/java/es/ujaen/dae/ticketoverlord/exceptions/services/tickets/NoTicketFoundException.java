package es.ujaen.dae.ticketoverlord.exceptions.services.tickets;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoTicketFoundException extends RuntimeException {
}
