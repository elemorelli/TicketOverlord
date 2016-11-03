package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Venue;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("VenuesDAO")
public class VenueDAOHibernateImpl implements VenueDAO {
    @Resource(name = "recintosTestData")
    private List<Venue> venues;

    public VenueDAOHibernateImpl() {
        this.venues = new ArrayList<>();
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