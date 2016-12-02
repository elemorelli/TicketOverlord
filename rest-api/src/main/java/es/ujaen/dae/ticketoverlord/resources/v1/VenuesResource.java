package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;
import es.ujaen.dae.ticketoverlord.services.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/venues")
public class VenuesResource {
    @Autowired
    private VenuesService venuesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getVenues() {

        List<VenueDTO> venues = venuesService.getVenues();
        List<String> links = new ArrayList<>();
        for (VenueDTO venue : venues) {
            links.add(venue.getLink(Link.REL_SELF).getHref());
        }
        return links;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}")
    public VenueDTO getVenueData(@PathVariable Integer venueId) {
        return venuesService.getVenue(venueId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}/zones")
    public Map<Character, ZoneDTO> getZones(@PathVariable Integer venueId) {
        return venuesService.getVenue(venueId).getZones();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}/zones/{zoneName}")
    public ZoneDTO getZone(@PathVariable Integer venueId, @PathVariable Character zoneName) {
        return venuesService.getVenue(venueId).getZones().get(zoneName);
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/{venueId}", consumes = "application/json")
//    public VenueDTO modifyVenue(@PathVariable Integer venueId, @RequestBody VenueDTO venueDTO) {
//        venueDTO.setVenueId(venueId);
//        return venuesService.modifyVenue(venueDTO);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
//    public VenueDTO addVenue(@RequestBody VenueDTO venueDTO) {
//        return venuesService.addNewVenue(venueDTO);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{venueId}", consumes = "application/json")
//    public void deleteVenue(@PathVariable Integer venueId, @RequestBody VenueDTO venueDTO) {
//        venueDTO.setVenueId(venueId);
//        venuesService.deleteVenue(venueDTO);
//    }
}
