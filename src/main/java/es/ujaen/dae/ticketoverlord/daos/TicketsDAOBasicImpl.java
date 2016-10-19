package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsDAOBasicImpl implements TicketsDAO {
    private UsersDAO usersDAO;
    private List<Ticket> tickets;

    public TicketsDAOBasicImpl() {
        tickets = new ArrayList<>();
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
