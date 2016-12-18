package es.ujaen.dae.ticketoverlord.client.utilities;

import es.ujaen.dae.ticketoverlord.client.dtos.UserDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsersTemplate extends ApiTemplate {
    private static final String URL = BASE_URL + "users/";
    private static final String URL_ID = URL + "{usersId}/";

    public static void addUser(UserDTO userDTO) {
        new RestTemplate().exchange(URL, HttpMethod.PUT, getRequest(userDTO), UserDTO.class);
    }

    public static List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        ResponseEntity<String[]> responseEntity = new RestTemplate().exchange(URL, HttpMethod.GET, getRequest(null), String[].class);
        String[] links = responseEntity.getBody();
        for (String link : links) {
            users.add(new RestTemplate().exchange(link, HttpMethod.GET, getRequest(null), UserDTO.class).getBody());
        }
        return users;
    }

    public static UserDTO getUser(Integer userId) {
        return new RestTemplate().exchange(URL_ID, HttpMethod.GET, getRequest(null), UserDTO.class, userId).getBody();
    }

    public static UserDTO getUser(String username) {
        return new RestTemplate().exchange(URL_ID, HttpMethod.GET, getRequest(null), UserDTO.class, username).getBody();
    }

    public static void updateUser(UserDTO userDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.POST, getRequest(userDTO), UserDTO.class, userDTO.getUserId());
    }

    public static void deleteUser(UserDTO userDTO) {
        new RestTemplate().exchange(URL_ID, HttpMethod.DELETE, getRequest(null), UserDTO.class, userDTO.getUserId());
    }
}
