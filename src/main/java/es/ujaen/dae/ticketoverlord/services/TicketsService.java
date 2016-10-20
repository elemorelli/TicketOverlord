package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.*;
import es.ujaen.dae.ticketoverlord.exceptions.NoTicketsAvailableException;

import java.util.List;

public interface TicketsService {
    void buyTicket(UserDTO user,  EventDTO event, PricePerZoneDTO price, Integer ticketsToBuy) throws NoTicketsAvailableException;

    List<TicketDTO> listTickets(UserDTO user);
}
