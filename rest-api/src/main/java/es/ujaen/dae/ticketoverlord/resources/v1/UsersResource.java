package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.BaseResource.API;

@RestController
@RequestMapping(API + "/users")
public class UsersResource {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return usersService.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUserData(@PathVariable Integer userId) {
        return usersService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/tickets")
    public List<TicketDTO> getUserTickets(@PathVariable Integer userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        return ticketsService.listTicketsByUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}", consumes = "application/json")
    public UserDTO modifyUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        return usersService.modifyUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return usersService.addNewUser(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}", consumes = "application/json")
    public void deleteUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        userDTO.setUserId(userId);
        usersService.deleteUser(userDTO);
    }
}
