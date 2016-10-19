package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.AdminOperation;
import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.models.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private EventsDAO eventsDAO;

    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByName(String name) {

        return getEventDTOs(eventsDAO.selectEventsByName(name));
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByNameAndCity(String name, String city) {

        return getEventDTOs(eventsDAO.selectEventsByNameAndCity(name, city));
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByDateAndType(LocalDate date, String type) {

        return getEventDTOs(eventsDAO.selectEventsByDateAndType(date, type));
    }

    @Override
    @LoggedUserOperation
    public List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        return getEventDTOs(eventsDAO.selectEventsByDateTypeAndCity(date, type, city));
    }

    @Override
    @AdminOperation
    public void addNewEvent(EventDTO event) {
    }

    private List<EventDTO> getEventDTOs(List<Event> filteredEvents) {
        List<EventDTO> events = new ArrayList<>();
        for (Event event : filteredEvents) {
            events.add(new EventDTO(event));
        }
        return events;
    }
}
