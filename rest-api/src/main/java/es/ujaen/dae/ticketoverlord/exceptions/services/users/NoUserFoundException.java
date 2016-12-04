package es.ujaen.dae.ticketoverlord.exceptions.services.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No user found")
public class NoUserFoundException extends RuntimeException {
    public NoUserFoundException() {
        super();
    }

    public NoUserFoundException(String message) {
        super(message);
    }
}
