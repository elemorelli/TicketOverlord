package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.List;

public class VenuesServiceImpl implements VenuesService {
    private List<Venue> venues;

    @Override
    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
