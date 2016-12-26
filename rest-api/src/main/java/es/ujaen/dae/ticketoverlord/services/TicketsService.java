package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.exceptions.services.tickets.NoTicketsAvailableException;

import java.util.List;

public interface TicketsService {
    List<TicketDTO> getTickets();

    List<TicketDTO> getTicketsByUser(String username);

    TicketDTO getTicket(Integer ticketId);

    TicketDTO buyTicket(TicketDTO ticketDTO) throws NoTicketsAvailableException, TicketTransactionException;

    TicketDTO modifyTicket(TicketDTO ticket);

    void deleteTicket(TicketDTO ticket);
}
