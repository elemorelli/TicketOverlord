package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;

import java.time.LocalDate;
import java.util.List;

public interface VentaTicketsService {
    void addNewUser(UserDTO user);

    Boolean userExists(UserDTO user);

    boolean authenticateUser(UserDTO user);

    UserDTO getUserData(UserDTO user);

    List<EventDTO> findEventsByName(String name);

    List<EventDTO> findEventsByNameAndCity(String name, String city);

    List<EventDTO> findEventsByDateAndType(LocalDate date, String type);

    List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city);

    void buyTicket(EventDTO event, ZoneDTO zone);

    List<TicketDTO> listTickets(UserDTO user);

    void addEvent(EventDTO event);
}
