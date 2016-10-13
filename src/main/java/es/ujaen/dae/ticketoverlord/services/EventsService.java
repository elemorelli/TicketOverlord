package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {

    List<EventDTO> findEventsByName(String name);

    List<EventDTO> findEventsByNameAndCity(String name, String city);

    List<EventDTO> findEventsByDateAndType(LocalDate date, String type);

    List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city);

    void addNewEvent(EventDTO event);
}
