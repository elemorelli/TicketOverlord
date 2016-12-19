package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.ForbiddenAccessException;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public UserDTO getUserData(@PathVariable String username) {

        verifyAuthenticatedUser(username);
        return usersService.getUser(username);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{username}/tickets")
    public List<String> getUserTickets(@PathVariable String username) {

        verifyAuthenticatedUser(username);

        UserDTO userDTO = usersService.getUser(username);

        List<TicketDTO> tickets = ticketsService.getTicketsByUser(userDTO);
        List<String> links = new ArrayList<>();
        for (TicketDTO ticket : tickets) {
            links.add(ticket.getLink(Link.REL_SELF).getHref());
        }
        return links;
    }

    private void verifyAuthenticatedUser(@PathVariable String username) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equalsIgnoreCase("admin") && !authenticatedUser.equalsIgnoreCase(username)) {
            throw new ForbiddenAccessException();
        }
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        usersService.deleteUser(userDTO);
    }
}
