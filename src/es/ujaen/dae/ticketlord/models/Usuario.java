package es.ujaen.dae.ticketlord.models;

import java.util.List;

public class Usuario {
    private Long token;
    private String nombre;
    private String password;
    private List<Ticket> tickets;

    public Usuario() {
    }

    public Usuario(Long token, String nombre, String password, List<Ticket> tickets) {
        this.token = token;
        this.nombre = nombre;
        this.password = password;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "token=" + token +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
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
}
