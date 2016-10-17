package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsServiceImpl implements TicketsService {
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketsServiceImpl() {
        tickets = new ArrayList<>();
    }

    @Override
    @LoggedUserOperation
    public void buyTicket(UserDTO user, EventDTO event, PricePerZoneDTO price, Integer ticketsToBuy) {

        Ticket ticket = new Ticket();

        // TODO: Add Ticket to User
    }

    @Override
    @LoggedUserOperation
    public List<TicketDTO> listTickets(UserDTO user) {

        List<TicketDTO> tickets = new ArrayList<>();
        for (Ticket ticket : this.tickets) {
            tickets.add(new TicketDTO(ticket));
        }
        return tickets;
    }
}
