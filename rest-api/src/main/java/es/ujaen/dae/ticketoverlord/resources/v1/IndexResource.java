package es.ujaen.dae.ticketoverlord.resources.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(API)
public class IndexResource {
    final static String API = "/api/v1";

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, String> getHomeLinks() throws NoSuchMethodException {
        Map<String, String> links = new LinkedHashMap<>();

        links.put("Users", linkTo(UsersResource.class.getMethod("getUsers"), 2L).withSelfRel().getHref());
        links.put("Venues", linkTo(VenuesResource.class.getMethod("getVenues"), 2L).withSelfRel().getHref());
        links.put("Events", linkTo(EventsResource.class.getMethod("getEvents", String.class, String.class, String.class, String.class), 2L).withSelfRel().getHref());
        links.put("Tickets", linkTo(TicketsResource.class.getMethod("getTickets"), 2L).withSelfRel().getHref());

        return links;
    }
}
