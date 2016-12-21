package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.Exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.client.dtos.TicketDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TicketsTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "tickets/";
    private static final String URL_ID = URL + "{ticketsId}/";

    public static void addTicket(String user, String pass, TicketDTO ticketDTO) throws TicketTransactionException {
        try {
            getTemplate(user, pass).exchange(URL, HttpMethod.PUT, new HttpEntity<>(ticketDTO), TicketDTO.class);
        } catch (Exception e) {
            throw new TicketTransactionException(e);
        }
    }

    public static List<TicketDTO> getAllTickets(String user, String pass) {
        List<TicketDTO> tickets = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(URL, HttpMethod.GET, new HttpEntity<>(null), String[].class);
        String[] links = responseEntity.getBody();
        for (String link : links) {
            tickets.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), TicketDTO.class).getBody());
        }
        return tickets;
    }

    public static TicketDTO getTicket(String user, String pass, Integer ticketId) {
        try {
            return getTemplate(user, pass).exchange(URL_ID, HttpMethod.GET, new HttpEntity<>(null), TicketDTO.class, ticketId).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static void updateTicket(String user, String pass, TicketDTO ticketDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.POST, new HttpEntity<>(ticketDTO), TicketDTO.class, ticketDTO.getTicketId());
    }

    public static void deleteTicket(String user, String pass, TicketDTO ticketDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.DELETE, new HttpEntity<>(null), TicketDTO.class, ticketDTO.getTicketId());
    }
}
