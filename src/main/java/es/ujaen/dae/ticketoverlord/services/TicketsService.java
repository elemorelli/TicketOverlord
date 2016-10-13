package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;

import java.util.List;

public interface TicketsService {
    void buyTicket(EventDTO event, ZoneDTO zone);

    List<TicketDTO> listTickets(UserDTO user);
}
