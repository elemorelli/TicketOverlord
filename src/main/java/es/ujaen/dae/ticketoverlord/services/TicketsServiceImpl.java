package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.NoTicketsAvailableException;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @LoggedUserOperation
    public void buyTicket(UserDTO userDTO, EventDTO eventDTO, PricePerZoneDTO priceDTO, Integer ticketsToBuy) throws NoTicketsAvailableException, TicketTransactionException {

        try {
            Ticket ticket = new Ticket();

            Event event = eventsDAO.selectEventById(eventDTO.getId());
            ticket.setEvent(event);
            ticket.setQuantity(ticketsToBuy);

            PricePerZone pricePerZone = event.getPricePerZones().get(priceDTO.getZone().getName());

            ticket.setZone(pricePerZone.getZone());
            ticket.setPrice(pricePerZone.getPrice());

            Integer availableSeats = pricePerZone.getAvailableSeats();
            if (ticketsToBuy > 0 && ticketsToBuy <= availableSeats) {
                pricePerZone.setAvailableSeats(availableSeats - ticketsToBuy);
            } else {
                throw new NoTicketsAvailableException();
            }

            User user = usersDAO.selectUserById(userDTO.getId());
            ticket.setUser(user);
            ticketsDAO.insertTicket(ticket);

            // TODO: Revisar porque no actualiza los asientos
            eventsDAO.updateEvent(event);
        } catch (RuntimeException e) {
            throw new TicketTransactionException(e);
        }
    }

    @Override
    @LoggedUserOperation
    public List<TicketDTO> listTicketsByUser(UserDTO user) {

        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : ticketsDAO.selectTicketsByUser(user.getId())) {

            ticketDTOs.add(new TicketDTO(ticket));
        }
        return ticketDTOs;
    }
}
