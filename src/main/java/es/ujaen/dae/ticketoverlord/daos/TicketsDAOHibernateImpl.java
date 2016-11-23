package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketUpdateException;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("TicketsDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TicketsDAOHibernateImpl implements TicketsDAO {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UsersDAO usersDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Ticket selectTicketByNumber(Integer ticketNumber) {

        try {
            Ticket ticket = em.find(Ticket.class, ticketNumber);
            return ticket;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Ticket> selectAllTickets() {

        return em.createQuery("SELECT t FROM Ticket t", Ticket.class)
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Cacheable("ticketsCache")
    public List<Ticket> selectTicketsByUser(Integer id) {
        return usersDAO.selectUserById(id).getTickets();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void insertTicket(Ticket ticket) {

        try {
            em.persist(ticket);
            em.flush();
        } catch (Exception e) {
            throw new TicketInsertionException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void updateTicket(Ticket ticket) {

        try {
            em.merge(ticket);
            em.flush();
        } catch (Exception e) {
            throw new TicketUpdateException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "ticketsCache", key = "#ticket.getUser().getId()")
    public void delete(Ticket ticket) {
        try {
            em.remove(em.find(Ticket.class, ticket.getId()));
            em.flush();
        } catch (Exception e) {
            throw new TicketRemovalException(e);
        }
    }
}
