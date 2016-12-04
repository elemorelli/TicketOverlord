package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Event;

import java.util.List;
import java.util.Map;

public interface EventsDAO {
    Event selectEventById(Integer eventId);

    List<Event> selectAllEvents();

    List<Event> selectEventsWithFilters(Map<String, String> filters);

    void insertEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);
}
