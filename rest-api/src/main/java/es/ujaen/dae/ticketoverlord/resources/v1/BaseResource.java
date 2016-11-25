package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.resources.v1.BaseResource.API;

@RestController
@RequestMapping(API)
public class BaseResource {
    @Autowired
    private UsersService usersService;
    final static String API = "/api/v1";

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, String> getHomeLinks() {
        Map<String, String> links = new HashMap<>();
        links.put("Users", "http://localhost:8080/ticketoverlord" + API + "/users/");
        return links;
    }
}
