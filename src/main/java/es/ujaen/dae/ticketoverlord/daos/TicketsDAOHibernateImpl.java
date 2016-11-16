package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketUpdateException;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("TicketsDAO")
public class TicketsDAOHibernateImpl implements TicketsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Ticket selectTicketByNumber(Integer ticketNumber) {

        try {
            Ticket ticket = em.find(Ticket.class, ticketNumber);
            return ticket;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Ticket> selectAllTickets() {

        return em.createQuery("SELECT t FROM Ticket t", Ticket.class)
                .getResultList();
    }

    @Override
    @Cacheable("ticketsCache")
    public List<Ticket> selectTicketsByUser(Integer id) {

        return em.createQuery("SELECT t FROM Ticket t " +
                "WHERE t.user.id = :id", Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void insertTicket(Ticket ticket) {

        try {
            em.persist(ticket);
        } catch (Exception e) {
            throw new TicketInsertionException(e);
        }
    }

    @Override
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void updateTicket(Ticket ticket) {

        try {
            em.merge(ticket);
        } catch (Exception e) {
            throw new TicketUpdateException(e);
        }
    }

    @Override
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void delete(Ticket ticket) {
        try {
            em.remove(em.find(Ticket.class, ticket.getId()));
        } catch (Exception e) {
            throw new TicketRemovalException(e);
        }
    }
}
