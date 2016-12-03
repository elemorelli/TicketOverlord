package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventUpdateException;
import es.ujaen.dae.ticketoverlord.models.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository("EventsDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EventsDAOJPAImpl implements EventsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Event selectEventById(Integer eventId) {

        return em.find(Event.class, eventId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Event> selectAllEvents() {

        return em.createQuery("SELECT e FROM Event e", Event.class)
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Event> selectEventsByName(String eventName) {

        return em.createQuery("SELECT e FROM Event e " +
                "WHERE UPPER(e.name) LIKE UPPER(:eventname)", Event.class)
                .setParameter("eventname", "%" + eventName + "%")
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Event> selectEventsByNameAndCity(String eventName, String eventCity) {

        return em.createQuery("SELECT e FROM Event e " +
                "WHERE UPPER(e.name) LIKE UPPER(:eventname) " +
                "AND UPPER(e.venue.city) LIKE UPPER(:cityname)", Event.class)
                .setParameter("eventname", "%" + eventName + "%")
                .setParameter("cityname", "%" + eventCity + "%")
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Event> selectEventsByDateAndType(LocalDate eventDate, String eventType) {

        return em.createQuery("SELECT e FROM Event e " +
                "WHERE e.date = :eventdate " +
                "AND UPPER(e.type) LIKE UPPER(:eventtype)", Event.class)
                .setParameter("eventdate", "%" + eventDate + "%")
                .setParameter("eventtype", "%" + eventType + "%")
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Event> selectEventsByDateTypeAndCity(LocalDate eventDate, String eventType, String eventCity) {

        return em.createQuery("SELECT e FROM Event e " +
                "WHERE e.date = :eventdate " +
                "AND UPPER(e.type) LIKE UPPER(:eventtype) " +
                "AND UPPER(e.venue.city) LIKE UPPER(:cityname)", Event.class)
                .setParameter("eventdate", "%" + eventDate + "%")
                .setParameter("eventtype", "%" + eventType + "%")
                .setParameter("cityname", "%" + eventCity + "%")
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertEvent(Event event) {
        try {
            em.persist(event);
            em.flush();
        } catch (Exception e) {
            throw new EventInsertionException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEvent(Event event) {
        try {
            em.merge(event);
            em.flush();
        } catch (Exception e) {
            throw new EventUpdateException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEvent(Event event) {
        try {
            em.remove(em.find(Event.class, event.getEventId()));
            // em.remove(event);
            em.flush();
        } catch (Exception e) {
            throw new EventRemovalException(e);
        }
    }
}
