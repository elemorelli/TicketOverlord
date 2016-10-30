package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketsDAOBasicImpl implements TicketsDAO {
    @Autowired
    private UsersDAO usersDAO;
    // @Resource(name="")
    private List<Ticket> tickets;

    public TicketsDAOBasicImpl() {
        tickets = new ArrayList<>();
    }

    @Override
    public Ticket selectTicketByNumber(Integer ticketNumber) {

        return null;
    }

    @Override
    public List<Ticket> selectTicketsByUser(String username) {

        return usersDAO.selectUserByName(username).getTickets();
    }

    @Override
    public void insertTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        // TODO
    }

    @Override
    public void delete(Ticket ticket) {
        // TODO
    }
}
