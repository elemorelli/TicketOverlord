package es.ujaen.dae.ticketlord.models;

import java.math.BigDecimal;

public class Ticket {
    private Evento evento;
    private Zona zona;
    private BigDecimal precio;

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
