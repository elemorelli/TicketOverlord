package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.VenueDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class VenuesTemplate extends ApiTemplate {
    private final static String URL = BASE_URL + "venues/";
    private final static String URL_ID = URL + "{venuesId}/";

    public static void addVenue(String user, String pass, VenueDTO venueDTO) {
        getTemplate(user, pass).exchange(URL, HttpMethod.PUT, new HttpEntity<>(venueDTO), VenueDTO.class);
    }

    public static List<VenueDTO> getAllVenues(String user, String pass) {
        List<VenueDTO> venues = new ArrayList<>();

        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(URL, HttpMethod.GET, new HttpEntity<>(null), String[].class);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            venues.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), VenueDTO.class).getBody());
        }
        return venues;
    }

    public static VenueDTO getVenue(String user, String pass, Integer venueId) {
        try {
            return getTemplate(user, pass).exchange(URL_ID, HttpMethod.GET, new HttpEntity<>(null), VenueDTO.class, venueId).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static void updateVenue(String user, String pass, VenueDTO venueDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.POST, new HttpEntity<>(venueDTO), VenueDTO.class, venueDTO.getVenueId());
    }

    public static void deleteVenue(String user, String pass, VenueDTO venueDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.DELETE, new HttpEntity<>(null), VenueDTO.class, venueDTO.getVenueId());
    }
}
