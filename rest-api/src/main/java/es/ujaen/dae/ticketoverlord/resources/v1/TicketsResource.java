package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.exceptions.services.ForbiddenAccessException;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/tickets")
public class TicketsResource {
    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TicketDTO> getTickets() {

        return ticketsService.getTickets();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ticketId}")
    public TicketDTO getTicket(@PathVariable Integer ticketId) {
        return ticketsService.getTicket(ticketId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{ticketId}", consumes = "application/json")
    public TicketDTO modifyTicket(@PathVariable Integer ticketId, @RequestBody TicketDTO ticketDTO) {
        ticketDTO.setTicketId(ticketId);
        return ticketsService.modifyTicket(ticketDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public TicketDTO addTicket(@RequestBody TicketDTO ticketDTO) {

        verifyAuthenticatedUser(ticketDTO.getUserId());

        return ticketsService.buyTicket(ticketDTO);
    }

    private void verifyAuthenticatedUser(Integer userId) {

        UserDTO user = usersService.getUser(userId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equalsIgnoreCase("admin") && !authenticatedUser.equalsIgnoreCase(user.getUsername())) {
            throw new ForbiddenAccessException();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticketId);
        ticketsService.deleteTicket(ticketDTO);
    }
}
