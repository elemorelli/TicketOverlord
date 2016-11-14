package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.List;

public interface VenueDAO {

    Venue selectVenueById(Integer id);

    List<Venue> selectAllVenues();

    void insertVenue(Venue venue);

    void updateVenue(Venue venue);

    void deleteVenue(Venue venue);
}
