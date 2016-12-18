package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.VenueDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class VenuesTemplate extends ApiTemplate {
    private final static String URL = BASE_URL + "venues/";
    private final static String URL_ID = URL + "{venuesId}/";

    public static void addVenue(VenueDTO venueDTO) {
        new RestTemplate().exchange(URL, HttpMethod.PUT, getRequest(venueDTO), VenueDTO.class);
    }

    public static List<VenueDTO> getAllVenues() {
        List<VenueDTO> venues = new ArrayList<>();

        ResponseEntity<String[]> responseEntity = new RestTemplate().exchange(URL, HttpMethod.GET, getRequest(null), String[].class);
        String[] links = responseEntity.getBody();

        for (String link : links) {
            venues.add(new RestTemplate().exchange(link, HttpMethod.GET, getRequest(null), VenueDTO.class).getBody());
        }
        return venues;
    }

    public static VenueDTO getVenue(Integer venueId) {

        return new RestTemplate().exchange(URL_ID, HttpMethod.GET, getRequest(null), VenueDTO.class, venueId).getBody();
    }

    public static void updateVenue(VenueDTO venueDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.POST, getRequest(venueDTO), VenueDTO.class, venueDTO.getVenueId());
    }

    public static void deleteVenue(VenueDTO venueDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.DELETE, getRequest(null), VenueDTO.class, venueDTO.getVenueId());
    }
}
