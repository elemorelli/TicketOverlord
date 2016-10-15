package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.AdminOperation;
import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.models.Event;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByName(String name) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), name)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByNameAndCity(String name, String city) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), name)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByDateAndType(LocalDate date, String type) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    @AdminOperation
    public void addNewEvent(EventDTO event) {
    }
}
