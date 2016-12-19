package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.client.dtos.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class UsersTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "users/";
    private static final String URL_ID = URL + "{usersId}/";
    private static final String URL_TICKETS = URL_ID + "tickets/";

    public static void addUser(String user, String pass, UserDTO userDTO) {
        getTemplate(user, pass).exchange(URL, HttpMethod.PUT, new HttpEntity<>(userDTO), UserDTO.class);
    }

    public static List<UserDTO> getAllUsers(String user, String pass) {
        List<UserDTO> users = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(URL, HttpMethod.GET, new HttpEntity<>(null), String[].class);
        String[] links = responseEntity.getBody();
        for (String link : links) {
            users.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), UserDTO.class).getBody());
        }
        return users;
    }

    public static UserDTO getUser(String user, String pass, Integer userId) {
        return getTemplate(user, pass).exchange(URL_ID, HttpMethod.GET, new HttpEntity<>(null), UserDTO.class, userId).getBody();
    }

    public static UserDTO getUser(String user, String pass, String username) {
        return getTemplate(user, pass).exchange(URL_ID, HttpMethod.GET, new HttpEntity<>(null), UserDTO.class, username).getBody();
    }

    public static List<TicketDTO> getUserTickets(String user, String pass, String username) {

        List<TicketDTO> tickets = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = getTemplate(user, pass).exchange(URL_TICKETS, HttpMethod.GET, new HttpEntity<>(null), String[].class, username);
        String[] links = responseEntity.getBody();
        for (String link : links) {
            tickets.add(getTemplate(user, pass).exchange(link, HttpMethod.GET, new HttpEntity<>(null), TicketDTO.class).getBody());
        }
        return tickets;
    }

    public static void updateUser(String user, String pass, UserDTO userDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.POST, new HttpEntity<>(userDTO), UserDTO.class, userDTO.getUserId());
    }

    public static void deleteUser(String user, String pass, UserDTO userDTO) {
        getTemplate(user, pass).exchange(URL_ID, HttpMethod.DELETE, new HttpEntity<>(null), UserDTO.class, userDTO.getUserId());
    }
}
