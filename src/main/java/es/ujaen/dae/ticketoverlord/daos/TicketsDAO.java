package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.util.List;

public interface TicketsDAO {
    Ticket selectTicketByNumber(Integer ticketNumber);

    List<Ticket> selectAllTickets();

    List<Ticket> selectTicketsByUser(String username);

    void insertTicket(Ticket ticket);

    void updateTicket(Ticket ticket);

    void delete(Ticket ticket);
}
