package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/tickets")
public class TicketsResource extends ApiResource {
    @Autowired
    private TicketsService ticketsService;

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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ticketId}")
    public void deleteTicket(@PathVariable Integer ticketId) {

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticketId);
        ticketsService.deleteTicket(ticketDTO);
    }
}
