package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.NoTicketsAvailableException;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;

import java.util.List;

public interface TicketsService {
    void buyTicket(UserDTO user, EventDTO event, PricePerZoneDTO price, Integer ticketsToBuy) throws NoTicketsAvailableException, TicketTransactionException;

    List<TicketDTO> listTicketsByUser(UserDTO user);
}
