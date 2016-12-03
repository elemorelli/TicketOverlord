package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestTemplates {
    private final static String BASE_URL = "http://localhost:8080/ticketoverlord/api/v1/";

    public static class Session {
        public static void login(UserDTO user) {
            new RestTemplate().put(BASE_URL + "login/", user);
        }

        public static void logout(UserDTO user) {
            new RestTemplate().delete(BASE_URL + "logout/", user);
        }
    }

    public static class Venues {
        private final static String URL = BASE_URL + "venues/";
        private final static String URL_ID = URL + "{venuesId}/";

        public static void addVenue(VenueDTO venueDTO) {
            new RestTemplate().put(URL, venueDTO);
        }

        public static List<VenueDTO> getAllVenues() {
            List<VenueDTO> venues = new ArrayList<>();
            ArrayList<String> links = new RestTemplate().getForObject(URL, (new ArrayList<String>()).getClass());
            for (String link : links) {
                venues.add(new RestTemplate().getForObject(link, VenueDTO.class));
            }
            return venues;
        }

        public static VenueDTO getVenue(Integer eventId) {
            return new RestTemplate().getForObject(URL_ID, VenueDTO.class, eventId);
        }

        public static void updateVenue(VenueDTO venueDTO) {
            new RestTemplate().postForObject(URL_ID, venueDTO, VenueDTO.class, venueDTO.getVenueId());
        }

        public static void deleteVenue(VenueDTO venueDTO) {
            new RestTemplate().delete(URL_ID, venueDTO.getVenueId());
        }
    }

    public static class Events {
        private static final String URL = BASE_URL + "events/";
        private static final String URL_ID = URL + "{eventsId}/";

        public static void addEvent(EventDTO eventDTO) {
            new RestTemplate().put(URL, eventDTO);
        }

        public static List<EventDTO> getAllEvents() {
            List<EventDTO> events = new ArrayList<>();
            ArrayList<String> links = new RestTemplate().getForObject(URL, (new ArrayList<String>()).getClass());
            for (String link : links) {
                events.add(new RestTemplate().getForObject(link, EventDTO.class));
            }
            return events;
        }

        public static EventDTO getEvent(Integer eventId) {
            return new RestTemplate().getForObject(URL_ID, EventDTO.class, eventId);
        }

        public static void updateEvent(EventDTO eventDTO) {
            new RestTemplate().postForObject(URL_ID, eventDTO, EventDTO.class, eventDTO.getEventId());
        }

        public static void deleteEvent(EventDTO eventDTO) {
            new RestTemplate().delete(URL_ID, eventDTO.getEventId());
        }
    }

    public static class Users {
        private static final String URL = BASE_URL + "users/";
        private static final String URL_ID = URL + "{usersId}/";

        public static void addUser(UserDTO userDTO) {
            new RestTemplate().put(URL, userDTO);
        }

        public static List<UserDTO> getAllUsers() {
            List<UserDTO> users = new ArrayList<>();
            ArrayList<String> links = new RestTemplate().getForObject(URL, (new ArrayList<String>()).getClass());
            for (String link : links) {
                users.add(new RestTemplate().getForObject(link, UserDTO.class));
            }
            return users;
        }

        public static UserDTO getUser(Integer eventId) {
            return new RestTemplate().getForObject(URL_ID, UserDTO.class, eventId);
        }

        public static UserDTO getUser(String username) {
            return new RestTemplate().getForObject(URL_ID, UserDTO.class, username);
        }

        public static void updateUser(UserDTO userDTO) {
            new RestTemplate().postForObject(URL_ID, userDTO, UserDTO.class, userDTO.getUserId());
        }

        public static void deleteUser(UserDTO userDTO) {
            new RestTemplate().delete(URL_ID, userDTO.getUserId());
        }
    }

    public static class Tickets {
        private static final String URL = BASE_URL + "tickets/";
        private  static final String URL_ID = URL + "{ticketsId}/";

        public static void addTicket(TicketDTO ticketDTO) {
            new RestTemplate().put(URL, ticketDTO);
        }

        public static List<TicketDTO> getAllTickets() {
            List<TicketDTO> tickets = new ArrayList<>();
            ArrayList<String> links = new RestTemplate().getForObject(URL, (new ArrayList<String>()).getClass());
            for (String link : links) {
                tickets.add(new RestTemplate().getForObject(link, TicketDTO.class));
            }
            return tickets;
        }

        public static TicketDTO getTicket(Integer eventId) {
            return new RestTemplate().getForObject(URL_ID, TicketDTO.class, eventId);
        }

        public static void updateTicket(TicketDTO ticketDTO) {
            new RestTemplate().postForObject(URL_ID, ticketDTO, TicketDTO.class, ticketDTO.getTicketId());
        }

        public static void deleteTicket(TicketDTO ticketDTO) {
            new RestTemplate().delete(URL_ID, ticketDTO.getTicketId());
        }
    }
}
