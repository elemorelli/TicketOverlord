package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.tickets.TicketUpdateException;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("TicketsDAO")
public class TicketsDAOHibernateImpl implements TicketsDAO {
    @Autowired
    private UsersDAO usersDAO;
    private Map<Integer, Ticket> tickets;
    @PersistenceContext
    private EntityManager em;

    public TicketsDAOHibernateImpl() {
        tickets = new HashMap<>();
    }

    @Override
    public Ticket selectTicketByNumber(Integer ticketNumber) {

        if (tickets.containsKey(ticketNumber)) {
            return tickets.get(ticketNumber);
        } else {
            try {
                Ticket ticket = em.find(Ticket.class, ticketNumber);
                tickets.put(ticketNumber, ticket);
                return ticket;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<Ticket> selectTicketsByUser(String username) {

        return usersDAO.selectUserByName(username).getTickets();
    }

    @Override
    public void insertTicket(Ticket ticket) {

        try {
            em.persist(ticket);
            tickets.put(ticket.getId(), ticket);
        } catch (Exception e) {
            throw new TicketInsertionException(e);
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {

        try {
            em.merge(ticket);
        } catch (Exception e) {
            throw new TicketUpdateException(e);
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try {
            em.remove(em.find(Ticket.class, ticket.getId()));
            tickets.remove(ticket.getId());
        } catch (Exception e) {
            throw new TicketRemovalException(e);
        }
    }
}
