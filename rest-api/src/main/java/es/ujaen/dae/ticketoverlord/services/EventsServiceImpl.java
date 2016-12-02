package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.AdminOperation;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.events.EventZoneMismatchException;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static es.ujaen.dae.ticketoverlord.AppInitializer.DATE_FORMAT;

@Component("EventsService")
public class EventsServiceImpl implements EventsService {
    @Autowired
    private EventsDAO eventsDAO;
    @Autowired
    private VenueDAO venueDAO;

    @Override
    public List<EventDTO> getEvents() {
        return getEventDTOs(eventsDAO.selectAllEvents());
    }

    @Override
    public EventDTO getEvent(Integer eventId) {
        return new EventDTO(eventsDAO.selectEventById(eventId));
    }

    @Override
    public List<EventDTO> findEventsByName(String name) {

        return getEventDTOs(eventsDAO.selectEventsByName(name));
    }

    @Override
    public List<EventDTO> findEventsByNameAndCity(String name, String city) {

        return getEventDTOs(eventsDAO.selectEventsByNameAndCity(name, city));
    }

    @Override
    public List<EventDTO> findEventsByDateAndType(LocalDate date, String type) {

        return getEventDTOs(eventsDAO.selectEventsByDateAndType(date, type));
    }

    @Override
    public List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        return getEventDTOs(eventsDAO.selectEventsByDateTypeAndCity(date, type, city));
    }

    @Override
    @AdminOperation
    public EventDTO addNewEvent(UserDTO user, EventDTO eventDTO) {

        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setType(eventDTO.getType());
        event.setDate(LocalDate.parse(eventDTO.getDate(), DATE_FORMAT));

        Venue venue = venueDAO.selectVenueById(eventDTO.getVenueId());
        event.setVenue(venue);

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
            event.addPricePerZones(pricePerZone);
        }

        eventsDAO.insertEvent(event);

        return new EventDTO(event);
    }

    private List<EventDTO> getEventDTOs(List<Event> filteredEvents) {
        List<EventDTO> events = new ArrayList<>();
        for (Event event : filteredEvents) {
            events.add(new EventDTO(event));
        }
        return events;
    }
}
