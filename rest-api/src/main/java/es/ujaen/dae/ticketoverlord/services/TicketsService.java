package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.exceptions.services.tickets.NoTicketsAvailableException;

import java.util.List;

public interface TicketsService {
    List<TicketDTO> getTickets(UserDTO userDTO);

    List<TicketDTO> getTicketsByUser(UserDTO user);

    TicketDTO getTicket(TicketDTO ticket);

    TicketDTO buyTicket(UserDTO userDTO, TicketDTO ticketDTO) throws NoTicketsAvailableException, TicketTransactionException;

    TicketDTO modifyTicket(TicketDTO ticket);

    void deleteTicket(TicketDTO ticket);
}
