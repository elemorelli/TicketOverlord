package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.client.dtos.PricePerZoneDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventsTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "events/";
    private static final String URL_ID = URL + "{eventsId}/";
    private static final String URL_AVAILABILITY = URL_ID + "availability/";
    private static final String URL_AVAILABILITY_ZONE = URL_AVAILABILITY + "{zoneName}/";

    public static void addEvent(EventDTO eventDTO) {
        new RestTemplate().exchange(URL, HttpMethod.PUT, getRequest(eventDTO), EventDTO.class);
    }

    public static List<EventDTO> getAllEvents(Map<String, String> filters) {

        String queryURL = URL + "?";
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            queryURL += entry.getKey() + "=" + entry.getValue() + "&";
        }

        List<EventDTO> events = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = new RestTemplate().exchange(queryURL, HttpMethod.GET, getRequest(null), String[].class);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            events.add(new RestTemplate().exchange(link, HttpMethod.GET, getRequest(null), EventDTO.class).getBody());
        }
        return events;
    }

    public static EventDTO getEvent(Integer eventId) {
        return new RestTemplate().exchange(URL_ID, HttpMethod.GET, getRequest(null), EventDTO.class, eventId).getBody();
    }

    public static List<PricePerZoneDTO> getEventAvailability(Integer eventId) {

        List<PricePerZoneDTO> pricePerZoneDTOs = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = new RestTemplate().exchange(URL_AVAILABILITY, HttpMethod.GET, getRequest(null), String[].class, eventId);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            pricePerZoneDTOs.add(new RestTemplate().exchange(link, HttpMethod.GET, getRequest(null), PricePerZoneDTO.class).getBody());
        }
        return pricePerZoneDTOs;
    }

    public static void updateEvent(EventDTO eventDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.POST, getRequest(eventDTO), EventDTO.class, eventDTO.getEventId());
    }

    public static void deleteEvent(EventDTO eventDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.DELETE, getRequest(null), EventDTO.class, eventDTO.getEventId());
    }
}
