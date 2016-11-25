package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.VenueDTO;

import java.util.List;

public interface VenuesService {
    List<VenueDTO> getVenues();
}
