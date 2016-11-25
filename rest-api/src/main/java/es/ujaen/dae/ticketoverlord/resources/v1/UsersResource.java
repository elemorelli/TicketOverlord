package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.BaseResource.API;

@RestController
@RequestMapping(API + "/users")
public class UsersResource {
    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<UserDTO> getUsers() {
        return usersService.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUserData(@PathVariable Integer userId) {
        return usersService.getUser(userId);
    }
}
