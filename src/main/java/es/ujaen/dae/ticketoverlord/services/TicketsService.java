package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.*;

import java.util.List;

public interface TicketsService {
    void buyTicket(UserDTO user,  EventDTO event, PricePerZoneDTO price, Integer ticketsToBuy);

    List<TicketDTO> listTickets(UserDTO user);
}
