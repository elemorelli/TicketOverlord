package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZoneDTO;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.User;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VentaTicketsServiceImpl implements VentaTicketsService {
    private List<Event> events;
    private List<Ticket> tickets;
    private List<Venue> venues;
    private List<User> users;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void addNewUser(UserDTO user) {

        User nuevoUser = new User(user.getName(), user.getPassword());
        nuevoUser.setUuidToken(UUID.randomUUID().toString());
        users.add(nuevoUser);
    }

    @Override
    public Boolean userExists(UserDTO userToQuery) {

        for (User user : this.users) {
            if (user.getName().equalsIgnoreCase(userToQuery.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean authenticateUser(UserDTO userToValidate) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userToValidate.getName())
                    && user.getPassword().equals(userToValidate.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDTO getUserData(UserDTO userToQuery) {

        for (User user : this.users) {
            if (user.getName().equalsIgnoreCase(userToQuery.getName())) {
                UserDTO foundUser = new UserDTO(user);
                if (foundUser.getName().equalsIgnoreCase("Administrador")) {
                    foundUser.setAdmin(true);
                }
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public List<EventDTO> findEventsByName(String name) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), name)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    public List<EventDTO> findEventsByNameAndCity(String name, String city) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (StringUtils.containsIgnoreCase(event.getName(), name)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    public List<EventDTO> findEventsByDateAndType(LocalDate date, String type) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    public List<EventDTO> findEventsByDateTypeAndCity(LocalDate date, String type, String city) {

        List<EventDTO> events = new ArrayList<>();
        for (Event event : this.events) {
            if (event.getDate().equals(date)
                    && StringUtils.containsIgnoreCase(event.getVenue().getCity(), city)) {
                events.add(new EventDTO(event));
            }
        }
        return events;
    }

    @Override
    public void buyTicket(EventDTO event, ZoneDTO zone) {
    }

    @Override
    public List<TicketDTO> listTickets(UserDTO user) {
        return null;
    }

    @Override
    public void addNewEvent(EventDTO event) {
    }
}
