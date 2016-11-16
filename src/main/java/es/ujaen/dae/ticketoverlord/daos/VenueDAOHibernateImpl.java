package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesUpdateException;
import es.ujaen.dae.ticketoverlord.models.Venue;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("VenuesDAO")
public class VenueDAOHibernateImpl implements VenueDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Cacheable("venuesCache")
    public Venue selectVenueById(Integer id) {

        try {
            Venue venue = em.find(Venue.class, id);
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
        } catch (Exception e) {
            throw new VenuesInsertionException(e);
        }
    }

    @Override
    @CacheEvict(value = "venuesCache", key = "#venue.getId()")
    public void updateVenue(Venue venue) {
        try {
            em.merge(venue);
        } catch (Exception e) {
            throw new VenuesUpdateException(e);
        }
    }

    @Override
    @CacheEvict(value = "venuesCache", key = "#venue.getId()")
    public void deleteVenue(Venue venue) {
        try {
            em.remove(em.find(Venue.class, venue.getId()));
            // em.remove(venue);
        } catch (Exception e) {
            throw new VenuesRemovalException(e);
        }
    }
}
