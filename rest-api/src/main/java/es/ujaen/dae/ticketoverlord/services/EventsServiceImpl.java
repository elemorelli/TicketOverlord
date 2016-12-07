package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.events.EventZoneMismatchException;
import es.ujaen.dae.ticketoverlord.exceptions.services.events.NoEventFoundException;
import es.ujaen.dae.ticketoverlord.exceptions.services.venues.NoVenueFoundException;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.AppInitializer.DATE_FORMAT;

@Component("EventsService")
public class EventsServiceImpl implements EventsService {
    @Autowired
    private EventsDAO eventsDAO;
    @Autowired
    private VenueDAO venueDAO;

    @Override
    public List<EventDTO> getEvents() {
        return getDTOsFromEvents(eventsDAO.selectAllEvents());
    }

    @Override
    public EventDTO getEvent(Integer eventId) {

        Event event = eventsDAO.selectEventById(eventId);
        if (event != null) {
            return new EventDTO(event);
        } else {
            throw new NoEventFoundException();
        }
    }

    @Override
    public List<EventDTO> getEventsWithFilters(Map<String, String> filters) {
        return getDTOsFromEvents(eventsDAO.selectEventsWithFilters(filters));
    }

    @Override
    public EventDTO addNewEvent(UserDTO userDTO, EventDTO eventDTO) {

        Event event = new Event();
        fillEventFromDTO(eventDTO, event);
        eventsDAO.insertEvent(event);
        return new EventDTO(event);
    }

    @Override
    public EventDTO modifyEvent(EventDTO eventDTO) {

        Event event = eventsDAO.selectEventById(eventDTO.getEventId());
        if (event != null) {
            fillEventFromDTO(eventDTO, event);
            eventsDAO.updateEvent(event);
            return new EventDTO(event);
        } else {
            throw new NoEventFoundException();
        }
    }

    @Override
    public void deleteEvent(EventDTO eventDTO) {

        Event event = eventsDAO.selectEventById(eventDTO.getEventId());
        if (event != null) {
            eventsDAO.deleteEvent(event);
        } else {
            throw new NoEventFoundException();
        }
    }

    private List<EventDTO> getDTOsFromEvents(List<Event> filteredEvents) {
        List<EventDTO> events = new ArrayList<>();
        for (Event event : filteredEvents) {
            events.add(new EventDTO(event));
        }
        return events;
    }

    private void fillEventFromDTO(EventDTO eventDTO, Event event) {
        event.setName(eventDTO.getName());
        event.setType(eventDTO.getType());
        event.setDate(LocalDate.parse(eventDTO.getDate(), DATE_FORMAT));

        Venue venue = venueDAO.selectVenueById(eventDTO.getVenueId());
        event.setVenue(venue);

        if (venue == null) {
            throw new NoVenueFoundException();
        }

        if (venue.getZones().size() != eventDTO.getPricesPerZone().size()) {
            throw new EventZoneMismatchException("The number of zones in the event does not match the venue");
        }

        List<PricePerZoneDTO> prices = eventDTO.getPricesPerZone();
        for (PricePerZoneDTO priceDTO : prices) {
            PricePerZone pricePerZone = new PricePerZone();
            pricePerZone.setPrice(priceDTO.getPrice());

            Zone zone = venue.getZones().get(priceDTO.getZoneName());
            if (zone == null) {
                throw new EventZoneMismatchException("The venue doesn't have a zone with name " + priceDTO.getZoneName());
            }

            pricePerZone.setZone(zone);
            pricePerZone.setAvailableSeats(zone.getSeats());
            event.addPricePerZone(pricePerZone);
        }
    }
}
