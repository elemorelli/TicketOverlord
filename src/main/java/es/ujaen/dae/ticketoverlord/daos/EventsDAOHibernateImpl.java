package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("EventsDAO")
public class EventsDAOHibernateImpl implements EventsDAO {
    private List<Event> events;

    public EventsDAOHibernateImpl() {
        this.events = new ArrayList<>();
    }

    @Override
    public Event selectEventByName(String eventName) {

        for (Event event : this.events) {
            if (event.getName().equalsIgnoreCase(eventName)) {
                return event;
            }
        }
        return null;
    }

    public List<Event> selectEventsByName(String eventName) {

        List<Event> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), eventName)) {
                events.add(event);
            }
        }
        return events;
    }

    public List<Event> selectEventsByNameAndCity(String eventName, String city) {

        List<Event> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), eventName)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public List<Event> selectEventsByDateAndType(LocalDate date, String type) {

        List<Event> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public List<Event> selectEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        List<Event> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public void insertEvent(Event event) {
        events.add(event);
    }

    @Override
    public void updateEvent(Event event) {
        // TODO
    }

    @Override
    public void deleteEvent(Event event) {
        // TODO
    }
}
