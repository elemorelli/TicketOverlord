package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import es.ujaen.dae.ticketoverlord.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("VenuesService")
public class VenuesServiceImpl implements VenuesService {
    @Autowired
    private VenueDAO venuesDAO;

    @Override
    public List<VenueDTO> getVenues() {

        List<VenueDTO> venueDTOs = new ArrayList<>();
        for (Venue venue : venuesDAO.selectAllVenues()) {
            venueDTOs.add(new VenueDTO(venue));
        }
        return venueDTOs;
    }

    @Override
    public VenueDTO getVenue(Integer venueId) {
        return new VenueDTO(venuesDAO.selectVenueById(venueId));
    }
}
