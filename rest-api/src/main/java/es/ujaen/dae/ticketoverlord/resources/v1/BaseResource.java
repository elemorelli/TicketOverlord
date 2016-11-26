package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.resources.v1.BaseResource.API;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(API)
public class BaseResource {
    @Autowired
    private SessionService sessionService;
    final static String API = "/api/v1";

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, String> getHomeLinks() throws NoSuchMethodException {
        Map<String, String> links = new HashMap<>();

        links.put("Login", linkTo(BaseResource.class.getMethod("login", UserDTO.class), 2L).withSelfRel().getHref());
        links.put("Logout", linkTo(BaseResource.class.getMethod("logout", UserDTO.class), 2L).withSelfRel().getHref());
        links.put("Users", linkTo(UsersResource.class.getMethod("getUsers"), 2L).withSelfRel().getHref());

        return links;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/login")
    public UserDTO login(@RequestBody UserDTO userDTO) {
        return sessionService.authenticateUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/logout")
    public void logout(@RequestBody UserDTO userDTO) {
        sessionService.logoutUser(userDTO);
    }
}
