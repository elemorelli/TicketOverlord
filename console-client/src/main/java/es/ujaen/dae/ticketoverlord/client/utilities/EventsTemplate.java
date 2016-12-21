package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.client.dtos.PricePerZoneDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventsTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "events/";
    private static final String URL_ID = URL + "{eventsId}/";
    private static final String URL_AVAILABILITY = URL_ID + "availability/";

    public static void addEvent(String user, String pass, EventDTO eventDTO) {
        getTemplate(user, pass).exchange(URL, HttpMethod.PUT, new HttpEntity<>(eventDTO), EventDTO.class);
    }

    public static List<EventDTO> getAllEvents(String user, String pass, Map<String, String> filters) {

        String queryURL = URL + "?";
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            queryURL += entry.getKey() + "=" + entry.getValue() + "&";
        }

        List<EventDTO> events = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(queryURL, HttpMethod.GET, new HttpEntity<>(null), String[].class);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            events.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), EventDTO.class).getBody());
        }
        return events;
    }

    public static EventDTO getEvent(String user, String pass, Integer eventId) {
        try {
            return getTemplate(user, pass).exchange(URL_ID, HttpMethod.GET, new HttpEntity<>(null), EventDTO.class, eventId).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<PricePerZoneDTO> getEventAvailability(String user, String pass, Integer eventId) {

        List<PricePerZoneDTO> pricePerZoneDTOs = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(URL_AVAILABILITY, HttpMethod.GET, new HttpEntity<>(null), String[].class, eventId);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            pricePerZoneDTOs.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), PricePerZoneDTO.class).getBody());
        }
        return pricePerZoneDTOs;
    }

    public static void updateEvent(String user, String pass, EventDTO eventDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.POST, new HttpEntity<>(eventDTO), EventDTO.class, eventDTO.getEventId());
    }

    public static void deleteEvent(String user, String pass, EventDTO eventDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.DELETE, new HttpEntity<>(null), EventDTO.class, eventDTO.getEventId());
    }
}
