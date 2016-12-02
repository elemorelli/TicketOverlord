package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsDAO {
    Event selectEventById(Integer eventId);

    List<Event> selectAllEvents();

    List<Event> selectEventsByName(String eventName);

    List<Event> selectEventsByNameAndCity(String eventName, String eventCity);

    List<Event> selectEventsByDateAndType(LocalDate eventDate, String eventType);

    List<Event> selectEventsByDateTypeAndCity(LocalDate eventDate, String eventType, String eventCity);

    void insertEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);
}
