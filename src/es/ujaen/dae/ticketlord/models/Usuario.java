package es.ujaen.dae.ticketlord.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String uuidToken;
    private String nombre;
    private String password;
    private List<Ticket> tickets;

    public Usuario() {
        this.tickets = new ArrayList<>();
    }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.tickets = new ArrayList<>();
    }

    public Usuario(String uuidToken, String nombre, String password, List<Ticket> tickets) {
        this.uuidToken = uuidToken;
        this.nombre = nombre;
        this.password = password;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "uuidToken=" + uuidToken +
                ", nombre='" + nombre + '\'' +
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
