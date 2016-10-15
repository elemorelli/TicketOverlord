package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;
import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.util.List;

public class TicketsServiceImpl implements TicketsService {
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    @LoggedUserOperation
    public void buyTicket(EventDTO event, ZoneDTO zone) {
    }

    @Override
    @LoggedUserOperation
    public List<TicketDTO> listTickets(UserDTO user) {
        return null;
    }
}
