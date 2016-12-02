package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.EventsService;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/events")
public class EventsResource {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getEvents() {

        List<EventDTO> events = eventsService.getEvents();
        List<String> links = new ArrayList<>();
        for (EventDTO event : events) {
            links.add(event.getLink(Link.REL_SELF).getHref());
        }
        return links;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{eventId}")
    public EventDTO getEventData(@PathVariable Integer eventId) {
        return eventsService.getEvent(eventId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{eventId}/availability")
    public List<String> getEventAvailability(@PathVariable Integer eventId) {

        List<PricePerZoneDTO> pricesPerZone = eventsService.getEvent(eventId).getPricesPerZone();
        List<String> links = new ArrayList<>();
        for (PricePerZoneDTO pricePerZoneDTO : pricesPerZone) {
            links.add(pricePerZoneDTO.getLink(Link.REL_SELF).getHref());
        }
        return links;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{eventId}/availability/{zoneName}")
    public PricePerZoneDTO getEventZoneAvailability(@PathVariable Integer eventId, @PathVariable Character zoneName) {

        return eventsService.getEvent(eventId).getPricesPerZone().get(zoneName);
    }

    //    @RequestMapping(method = RequestMethod.POST, value = "/{eventId}", consumes = "application/json")
    //    public EventDTO modifyEvent(@PathVariable Integer eventId, @RequestBody EventDTO eventDTO) {
    //        eventDTO.setVenueId(eventId);
    //        return eventsService.modifyEvent(eventDTO);
    //    }

    // TODO: Remove when the below method has only one @RequestBody
    public static class EventUserWrapper {
        private UserDTO user;
        private EventDTO event;

        public EventDTO getEvent() {
            return event;
        }

        public void setEvent(EventDTO event) {
            this.event = event;
        }

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public EventDTO addEvent(@RequestBody EventUserWrapper eventUserWrapper) {
        // TODO: Reemplazar el uso de UserDTO para autenticacion cuando tengamos Spring Security
        return eventsService.addNewEvent(eventUserWrapper.getUser(), eventUserWrapper.getEvent());
    }

    //    @RequestMapping(method = RequestMethod.DELETE, value = "/{eventId}", consumes = "application/json")
    //    public void deleteEvent(@PathVariable Integer eventId, @RequestBody EventDTO eventDTO) {
    //        eventDTO.setVenueId(eventId);
    //        eventsService.deleteEvent(eventDTO);
    //    }
}
