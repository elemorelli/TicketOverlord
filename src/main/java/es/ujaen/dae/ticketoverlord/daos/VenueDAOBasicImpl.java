package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.ArrayList;
import java.util.List;

public class VenueDAOBasicImpl implements VenueDAO {
    private List<Venue> venues;

    public VenueDAOBasicImpl() {
        this.venues = new ArrayList<>();
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public Venue selectVenueByName(String venueName) {

        for (Venue venue : venues) {
            if (venue.getName().equals(venueName)) {
                return venue;
            }
        }
        return null;
    }

    @Override
    public List<Venue> selectAllVenues() {
        return venues;
    }

    @Override
    public void insertVenue(Venue venue) {

    }

    @Override
    public void updateVenue(Venue venue) {

    }

    @Override
    public void deleteVenue(Venue venue) {

    }
}
