package es.ujaen.dae.ticketlord.models;

import java.math.BigDecimal;

public class Ticket {
    private BigDecimal precio;
    private Evento evento;
    private Zona zona;

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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
}
