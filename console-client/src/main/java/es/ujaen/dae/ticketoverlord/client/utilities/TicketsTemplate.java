package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.TicketDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TicketsTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "tickets/";
    private static final String URL_ID = URL + "{ticketsId}/";

    public static void addTicket(TicketDTO ticketDTO, String user, String pass) {
        new RestTemplate().exchange(URL, HttpMethod.PUT, getRequest(ticketDTO, user, pass), TicketDTO.class);
    }

    public static List<TicketDTO> getAllTickets() {
        List<TicketDTO> tickets = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = new RestTemplate().exchange(URL, HttpMethod.GET, getRequest(null), String[].class);
        String[] links = responseEntity.getBody();
        for (String link : links) {
            tickets.add(new RestTemplate().exchange(link, HttpMethod.GET, getRequest(null), TicketDTO.class).getBody());
        }
        return tickets;
    }

    public static TicketDTO getTicket(Integer ticketId) {
        return new RestTemplate().exchange(URL_ID, HttpMethod.GET, getRequest(null), TicketDTO.class, ticketId).getBody();
    }

    public static void updateTicket(TicketDTO ticketDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.POST, getRequest(ticketDTO), TicketDTO.class, ticketDTO.getTicketId());
    }

    public static void deleteTicket(TicketDTO ticketDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.DELETE, getRequest(null), TicketDTO.class, ticketDTO.getTicketId());
    }
}
