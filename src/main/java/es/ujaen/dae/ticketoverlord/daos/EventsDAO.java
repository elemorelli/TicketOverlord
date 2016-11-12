package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsDAO {

    Event selectEventById(Integer id);

    Event selectEventByName(String eventName);

    List<Event> selectAllEvents();

    List<Event> selectEventsByName(String eventName);

    List<Event> selectEventsByNameAndCity(String name, String city);

    List<Event> selectEventsByDateAndType(LocalDate date, String type);

    List<Event> selectEventsByDateTypeAndCity(LocalDate date, String type, String city);

    void insertEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Event event);
}
