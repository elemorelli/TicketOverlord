package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.services.EventsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<EventDTO> getEvents(
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

            return eventsService.getEventsWithFilters(filters);
        } else {
            return eventsService.getEvents();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{eventId}")
    public EventDTO getEventData(@PathVariable Integer eventId) {
        return eventsService.getEvent(eventId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{eventId}/availability")
    public List<PricePerZoneDTO> getEventAvailability(@PathVariable Integer eventId) {

        return eventsService.getEvent(eventId).getPricesPerZone();
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
        return eventsService.addNewEvent(eventDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(eventId);
        eventsService.deleteEvent(eventDTO);
    }
}
