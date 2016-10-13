package es.ujaen.dae.ticketoverlord.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String uuidToken;
    private String name;
    private String password;
    private List<Ticket> tickets;

    public User() {
        this.tickets = new ArrayList<>();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.tickets = new ArrayList<>();
    }

    public User(String uuidToken, String name, String password, List<Ticket> tickets) {
        this.uuidToken = uuidToken;
        this.name = name;
        this.password = password;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuidToken=" + uuidToken +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
