package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.annotations.LoggedUserOperation;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;
import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class VenuesServiceImpl implements VenuesService {
    private VenueDAO venuesDAO;

    public void setVenuesDAO(VenueDAO venuesDAO) {
        this.venuesDAO = venuesDAO;
    }

    @Override
    @LoggedUserOperation
    public List<VenueDTO> getVenues() {

        List<VenueDTO> venueDTOs = new ArrayList<>();
        for (Venue venue : venuesDAO.selectAllVenues()) {
            venueDTOs.add(new VenueDTO(venue));
        }
        return venueDTOs;
    }
}
