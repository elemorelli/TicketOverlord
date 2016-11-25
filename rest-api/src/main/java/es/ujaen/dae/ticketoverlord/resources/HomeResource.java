package es.ujaen.dae.ticketoverlord.resources;

import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeResource {
    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, String> getHomeLinks() {
        Map<String, String> links = new HashMap<>();
        links.put("Users", "http://localhost:8080/ticketoverlord/users/");
        return links;
    }
}
