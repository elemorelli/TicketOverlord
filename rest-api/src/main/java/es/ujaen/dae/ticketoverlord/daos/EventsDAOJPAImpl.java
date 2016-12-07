package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.events.EventUpdateException;
import es.ujaen.dae.ticketoverlord.models.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Event> selectEventsWithFilters(Map<String, String> filters) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> event = query.from(Event.class);

        List<Predicate> predicates = new ArrayList<>();

        String eventName = filters.get("name");
        if (StringUtils.isNotBlank(eventName)) {
            predicates.add(builder.like(event.get("name"), "%" + eventName + "%"));
        }
        String eventDate = filters.get("date");
        if (StringUtils.isNotBlank(eventDate)) {
            predicates.add(builder.equal(event.get("date"), eventDate));
        }
        String eventType = filters.get("type");
        if (StringUtils.isNotBlank(eventType)) {
            predicates.add(builder.equal(event.get("type"), eventType));
        }
        String eventCity = filters.get("city");
        if (StringUtils.isNotBlank(eventCity)) {
            predicates.add(builder.like(event.get("city"), "%" + eventCity + "%"));
        }

        query.select(event).where(predicates.toArray(new Predicate[]{}));

        return em.createQuery(query).getResultList();
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
            em.remove(em.find(Event.class, event.getId()));
            // em.remove(event);
            em.flush();
        } catch (Exception e) {
            throw new EventRemovalException(e);
        }
    }
}
