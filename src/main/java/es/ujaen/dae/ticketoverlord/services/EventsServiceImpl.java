package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.AdminOperation;
import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private EventsDAO eventsDAO;
    private VenueDAO venueDAO;

    public void setEventsDAO(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    public void setVenueDAO(VenueDAO venueDAO) {
        this.venueDAO = venueDAO;
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
    public void addNewEvent(EventDTO eventDTO) {

        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setType(eventDTO.getType());
        event.setDate(eventDTO.getDate());

        Venue venue = venueDAO.selectVenueByName(eventDTO.getVenue().getName());
        event.setVenue(venue);

        List<PricePerZoneDTO> prices = eventDTO.getPricesPerZone();
        for (PricePerZoneDTO priceDTO : prices) {
            PricePerZone pricePerZone = new PricePerZone();
            pricePerZone.setPrice(priceDTO.getPrice());

            for (Zone zone : venue.getZones()) {
                if (zone.getName().equals(priceDTO.getZone().getName())) {
                    pricePerZone.setZone(zone);
                    pricePerZone.setAvailableSeats(zone.getSeats());
                    break;
                }
            }
            event.addPricePerZones(pricePerZone);
        }

        eventsDAO.insertEvent(event);
    }

    private List<EventDTO> getEventDTOs(List<Event> filteredEvents) {
        List<EventDTO> events = new ArrayList<>();
        for (Event event : filteredEvents) {
            events.add(new EventDTO(event));
        }
        return events;
    }
}
