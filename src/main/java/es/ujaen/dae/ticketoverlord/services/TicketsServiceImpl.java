package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import es.ujaen.dae.ticketoverlord.models.User;

import java.util.ArrayList;
import java.util.List;

public class TicketsServiceImpl implements TicketsService {
    private TicketsDAO ticketsDAO;
    private UsersDAO usersDAO;
    private EventsDAO eventsDAO;

    public void setTicketsDAO(TicketsDAO ticketsDAO) {
        this.ticketsDAO = ticketsDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    @LoggedUserOperation
    public void buyTicket(UserDTO userDTO, EventDTO eventDTO, PricePerZoneDTO priceDTO, Integer ticketsToBuy) {

        Ticket ticket = new Ticket();

        Event event = eventsDAO.selectEventByName(eventDTO.getName());
        ticket.setEvent(event);

        for (PricePerZone pricePerZone : event.getPricePerZones()) {
            if (pricePerZone.getZone().getName().equals(priceDTO.getZone().getName())) {
                ticket.setZone(pricePerZone.getZone());
                ticket.setPrice(pricePerZone.getPrice());
            }
        }

        ticketsDAO.insertTicket(ticket);

        User user = usersDAO.selectUserByName(userDTO.getName());
        user.addTicket(ticket);
        usersDAO.updateUser(user);
    }

    @Override
    @LoggedUserOperation
    public List<TicketDTO> listTickets(UserDTO user) {

        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : ticketsDAO.selectTicketsByUser(user.getName())) {

            ticketDTOs.add(new TicketDTO(ticket));
        }
        return ticketDTOs;
    }
}
