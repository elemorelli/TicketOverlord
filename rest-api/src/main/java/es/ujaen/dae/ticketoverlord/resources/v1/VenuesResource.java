package es.ujaen.dae.ticketoverlord.resources.v1;

import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;
import es.ujaen.dae.ticketoverlord.services.VenuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.ujaen.dae.ticketoverlord.resources.v1.IndexResource.API;

@RestController
@RequestMapping(API + "/venues")
public class VenuesResource extends ApiResource {
    @Autowired
    private VenuesService venuesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<VenueDTO> getVenues() {

        return venuesService.getVenues();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}")
    public VenueDTO getVenueData(@PathVariable Integer venueId) {

        return venuesService.getVenue(venueId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}/zones")
    public List<ZoneDTO> getZones(@PathVariable Integer venueId) {

        return venuesService.getVenue(venueId).getZones();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{venueId}/zones/{zoneName}")
    public ZoneDTO getZone(@PathVariable Integer venueId, @PathVariable Character zoneName) {

        return venuesService.getVenue(venueId).getZone(zoneName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{venueId}", consumes = "application/json")
    public VenueDTO modifyVenue(@PathVariable Integer venueId, @RequestBody VenueDTO venueDTO) {

        venueDTO.setVenueId(venueId);
        return venuesService.modifyVenue(venueDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public VenueDTO addVenue(@RequestBody VenueDTO venueDTO) {

        return venuesService.addNewVenue(venueDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{venueId}")
    public void deleteVenue(@PathVariable Integer venueId) {

        VenueDTO venueDTO = new VenueDTO();
        venueDTO.setVenueId(venueId);
        venuesService.deleteVenue(venueDTO);
    }
}
