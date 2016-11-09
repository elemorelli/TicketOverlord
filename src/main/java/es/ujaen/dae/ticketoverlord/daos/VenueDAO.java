package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.Map;

public interface VenueDAO {
    Venue selectVenueByName(String venueName);

    Map<String, Venue> selectAllVenues();

    void insertVenue(Venue venue);

    void updateVenue(Venue venue);

    void deleteVenue(Venue venue);
}
