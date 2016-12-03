package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.venues.VenuesUpdateException;
import es.ujaen.dae.ticketoverlord.models.Venue;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("VenuesDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VenueDAOJPAImpl implements VenueDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Cacheable("venuesCache")
    public Venue selectVenueById(Integer id) {

        return em.find(Venue.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Venue> selectAllVenues() {

        return em.createQuery("SELECT v FROM Venue v", Venue.class)
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertVenue(Venue venue) {
        try {
            em.persist(venue);
            em.flush();
        } catch (Exception e) {
            throw new VenuesInsertionException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "venuesCache", key = "#venue.getId()")
    public void updateVenue(Venue venue) {
        try {
            em.merge(venue);
            em.flush();
        } catch (Exception e) {
            throw new VenuesUpdateException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "venuesCache", key = "#venue.getId()")
    public void deleteVenue(Venue venue) {
        try {
            em.remove(em.find(Venue.class, venue.getId()));
            // em.remove(venue);
            em.flush();
        } catch (Exception e) {
            throw new VenuesRemovalException(e);
        }
    }
}
