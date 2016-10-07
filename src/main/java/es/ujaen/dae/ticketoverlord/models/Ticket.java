package es.ujaen.dae.ticketoverlord.models;

import java.math.BigDecimal;

public class Ticket {
    private Evento evento;
    private Zona zona;
    private BigDecimal precio;

    public Ticket() {
    }

    public Ticket(Evento evento, Zona zona, BigDecimal precio) {
        this.evento = evento;
        this.zona = zona;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "evento=" + evento +
                ", zona=" + zona +
                ", precio=" + precio +
                '}';
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
