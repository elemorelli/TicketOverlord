package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;

import java.util.List;
import java.util.Map;

public interface EventsService {
    List<EventDTO> getEvents();

    EventDTO getEvent(Integer id);

    List<EventDTO> getEventsWithFilters(Map<String, String> filters);

    EventDTO addNewEvent(EventDTO eventDTO);

    EventDTO modifyEvent(EventDTO eventDTO);

    void deleteEvent(EventDTO eventDTO);
}
