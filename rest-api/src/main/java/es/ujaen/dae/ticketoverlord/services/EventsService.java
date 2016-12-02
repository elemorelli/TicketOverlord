package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {
    List<EventDTO> getEvents();

    EventDTO getEvent(Integer id);

    List<EventDTO> findEventsByName(String name);

    List<EventDTO> findEventsByNameAndCity(String name, String city);

    List<EventDTO> findEventsByDateAndType(LocalDate date, String type);

    List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city);

    EventDTO addNewEvent(UserDTO user, EventDTO event);
}
