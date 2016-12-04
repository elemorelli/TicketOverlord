package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.EventsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/events")
public class EventsResource {
    @Autowired
    private EventsService eventsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getEvents(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "date", defaultValue = "") String date,
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "city", defaultValue = "") String city) {

        List<EventDTO> events;
        if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(date)
                || StringUtils.isNotBlank(type) || StringUtils.isNotBlank(city)) {
            Map<String, String> filters = new HashMap<>();
            filters.put("name", name);
            filters.put("date", date);
            filters.put("type", type);
            filters.put("city", city);

            events = eventsService.getEventsWithFilters(filters);
        } else {
            events = eventsService.getEvents();
        }
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

        return eventsService.getEvent(eventId).getPricePerZone(zoneName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{eventId}", consumes = "application/json")
    public EventDTO modifyEvent(@PathVariable Integer eventId, @RequestBody EventDTO eventDTO) {
        eventDTO.setEventId(eventId);
        return eventsService.modifyEvent(eventDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
        return eventsService.addNewEvent(new UserDTO(), eventDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(eventId);
        eventsService.deleteEvent(eventDTO);
    }
}
