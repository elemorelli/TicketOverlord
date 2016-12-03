package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.util.List;

public interface TicketsDAO {
    Ticket selectTicketById(Integer ticketNumber);

    List<Ticket> selectAllTickets();

    List<Ticket> selectTicketsByUser(Integer id);

    void insertTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);
}
