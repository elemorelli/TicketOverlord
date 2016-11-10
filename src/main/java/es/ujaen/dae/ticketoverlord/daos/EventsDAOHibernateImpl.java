package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventUpdateException;
import es.ujaen.dae.ticketoverlord.models.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("EventsDAO")
public class EventsDAOHibernateImpl implements EventsDAO {
    private Map<String, Event> events;
    @PersistenceContext
    private EntityManager em;

    public EventsDAOHibernateImpl() {
        this.events = new HashMap<>();
    }

    @Override
    public Event selectEventByName(String eventName) {

        if (events.containsKey(eventName)) {
            return events.get(eventName);
        } else {
            try {
                Event event = em.createQuery("SELECT e FROM Event e WHERE e.name LIKE :eventname", Event.class)
                        .setParameter("eventname", "%" + eventName + "%")
                        .getSingleResult();
                events.put(event.getName(), event);
                return event;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public List<Event> selectEventsByName(String eventName) {

        try {
            return em.createQuery("SELECT e FROM Event e WHERE e.name LIKE :eventname", Event.class)
                    .setParameter("eventname", "%" + eventName + "%")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Event> selectEventsByNameAndCity(String eventName, String city) {

        try {
            return em.createQuery("SELECT e FROM Event e WHERE e.name LIKE :eventname AND e.venue.city LIKE :cityname", Event.class)
                    .setParameter("eventname", "%" + eventName + "%")
                    .setParameter("cityname", "%" + city + "%")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Event> selectEventsByDateAndType(LocalDate date, String type) {

        try {
            return em.createQuery("SELECT e FROM Event e WHERE e.date = :eventdate AND e.type LIKE :eventtype", Event.class)
                    .setParameter("eventdate", "%" + date + "%")
                    .setParameter("eventtype", "%" + type + "%")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Event> selectEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        try {
            return em.createQuery("SELECT e FROM Event e WHERE e.date = :eventdate AND e.type LIKE :eventtype AND e.venue.city LIKE :cityname", Event.class)
                    .setParameter("eventdate", "%" + date + "%")
                    .setParameter("eventtype", "%" + type + "%")
                    .setParameter("cityname", "%" + city + "%")
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insertEvent(Event event) {
        try {
            em.persist(event);
            events.put(event.getName(), event);
        } catch (Exception e) {
            throw new EventInsertionException(e);
        }
    }

    @Override
    public void updateEvent(Event event) {
        try {
            em.merge(event);
        } catch (Exception e) {
            throw new EventUpdateException(e);
        }
    }

    @Override
    public void deleteEvent(Event event) {
        try {
            em.remove(em.find(Event.class, event.getId()));
            // em.remove(event);
        } catch (Exception e) {
            throw new EventRemovalException(e);
        }
    }
}
