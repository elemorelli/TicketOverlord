package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesUpdateException;
import es.ujaen.dae.ticketoverlord.models.Venue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("VenuesDAO")
public class VenueDAOHibernateImpl implements VenueDAO {
    private Map<String, Venue> venues;
    @PersistenceContext
    private EntityManager em;

    public VenueDAOHibernateImpl() {
        this.venues = new HashMap<>();
    }

    @Override
    public Venue selectVenueById(Integer id) {

        try {
            Venue venue = em.find(Venue.class, id);
            venues.put(venue.getName(), venue);
            return venue;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Venue> selectAllVenues() {

        return em.createQuery("SELECT v FROM Venue v", Venue.class)
                .getResultList();
    }

    @Override
    public void insertVenue(Venue venue) {
        try {
            em.persist(venue);
            venues.put(venue.getName(), venue);
        } catch (Exception e) {
            throw new VenuesInsertionException(e);
        }
    }

    @Override
    public void updateVenue(Venue venue) {
        try {
            em.merge(venue);
        } catch (Exception e) {
            throw new VenuesUpdateException(e);
        }
    }

    @Override
    public void deleteVenue(Venue venue) {
        try {
            em.remove(em.find(Venue.class, venue.getId()));
            venues.remove(venue.getName());
            // em.remove(venue);
        } catch (Exception e) {
            throw new VenuesRemovalException(e);
        }
    }
}
