package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/users")
public class UsersResource {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getUsers() {

        List<UserDTO> users = usersService.getUsers();
        List<String> links = new ArrayList<>();
        for (UserDTO user : users) {
            links.add(user.getLink(Link.REL_SELF).getHref());
        }
        return links;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public UserDTO getUserData(@PathVariable Integer userId) {
        return usersService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/tickets")
    public List<String> getUserTickets(@PathVariable Integer userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);

        List<TicketDTO> tickets = ticketsService.listTicketsByUser(userDTO);
        List<String> links = new ArrayList<>();
        for (TicketDTO ticket : tickets) {
            // TODO: Cambiar al link de ticket cuando este su recurso
            //links.add(ticket.getLink(Link.REL_SELF).getHref());
            links.add("Ticket " + ticket.getTicketId());
        }
        return links;
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
