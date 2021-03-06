package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;

import java.util.List;

public interface VenuesService {
    List<VenueDTO> getVenues();

    VenueDTO getVenue(Integer venueId);

    VenueDTO addNewVenue(VenueDTO venueDTO);

    VenueDTO modifyVenue(VenueDTO venueDTO);

    void deleteVenue(VenueDTO venueDTO);
}
