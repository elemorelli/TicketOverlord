package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.exceptions.services.tickets.NoTicketFoundException;
import es.ujaen.dae.ticketoverlord.exceptions.services.tickets.NoTicketsAvailableException;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("TicketsService")
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    private TicketsDAO ticketsDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private EventsDAO eventsDAO;

    @Override
    @Transactional(readOnly = true)
    public List<TicketDTO> getTickets(UserDTO userDTO) {

        List<TicketDTO> tickets = new ArrayList<>();
        for (Ticket ticket : ticketsDAO.selectAllTickets()) {
            tickets.add(new TicketDTO(ticket));
        }
        return tickets;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDTO> getTicketsByUser(UserDTO user) {

        List<TicketDTO> tickets = new ArrayList<>();
        for (Ticket ticket : usersDAO.selectUserById(user.getUserId()).getTickets()) {
            tickets.add(new TicketDTO(ticket));
        }
        return tickets;
    }

    @Override
    @Transactional(readOnly = true)
    public TicketDTO getTicket(Integer ticketId) {

        Ticket ticket = ticketsDAO.selectTicketById(ticketId);
        if (ticket != null) {
            return new TicketDTO(ticket);
        } else {
            throw new NoTicketFoundException();
        }
    }

    @Override
    @Transactional(rollbackFor = {TicketTransactionException.class})
    public TicketDTO buyTicket(UserDTO userDTO, TicketDTO ticketDTO) throws NoTicketsAvailableException, TicketTransactionException {

        try {
            Ticket ticket = new Ticket();

            Event event = eventsDAO.selectEventById(ticketDTO.getEventId());
            ticket.setEvent(event);

            Integer ticketsToBuy = ticketDTO.getQuantity();
            ticket.setQuantity(ticketsToBuy);

            PricePerZone pricePerZone = event.getPricePerZones().get(ticketDTO.getZoneName());

            ticket.setZone(pricePerZone.getZone());
            ticket.setPrice(pricePerZone.getPrice());

            Integer availableSeats = pricePerZone.getAvailableSeats();
            if (ticketsToBuy > 0 && ticketsToBuy <= availableSeats) {
                pricePerZone.setAvailableSeats(availableSeats - ticketsToBuy);
            } else {
                throw new NoTicketsAvailableException();
            }

            User user = usersDAO.selectUserById(ticketDTO.getUserId());
            user.addTicket(ticket);
            ticket.setUser(user);

            ticketsDAO.insertTicket(ticket);
            usersDAO.updateUser(user);
            eventsDAO.updateEvent(event);

            return new TicketDTO(ticket);
        } catch (OptimisticLockingFailureException e) {
            throw new TicketTransactionException(e);
        }
    }

    @Override
    public TicketDTO modifyTicket(TicketDTO ticketDTO) {

        Ticket ticket = ticketsDAO.selectTicketById(ticketDTO.getTicketId());
        if (ticket != null) {

            User user = usersDAO.selectUserById(ticketDTO.getUserId());
            ticket.setUser(user);

            Event event = eventsDAO.selectEventById(ticketDTO.getEventId());
            ticket.setEvent(event);
            ticket.setZone(event.getVenue().getZones().get(ticketDTO.getZoneName()));

            // TODO: Rollback de la transaccion anterior, restableciendo la cantidad de tickets disponibles

            ticket.setPrice(ticketDTO.getPrice());
            ticket.setQuantity(ticketDTO.getQuantity());

            ticketsDAO.updateTicket(ticket);
            return new TicketDTO(ticket);
        } else {
            throw new NoTicketFoundException();
        }
    }

    @Override
    public void deleteTicket(TicketDTO ticketDTO) {

        Ticket ticket = ticketsDAO.selectTicketById(ticketDTO.getTicketId());

        // TODO: Rollback de la transaccion anterior, restableciendo la cantidad de tickets disponibles

        if (ticket != null) {
            ticketsDAO.deleteTicket(ticket);
        } else {
            throw new NoTicketFoundException();
        }
    }
}
