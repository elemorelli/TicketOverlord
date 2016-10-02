package es.ujaen.dae.ticketlord.dtos;

import java.math.BigDecimal;

public class TicketDTO {
    private BigDecimal precio;
    private EventoDTO evento;
    private ZonaDTO zona;

    @Override
    public String toString() {
        return "TicketDTO{" +
                "precio=" + precio +
                ", evento=" + evento +
                ", zona=" + zona +
                '}';
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }

    public ZonaDTO getZona() {
        return zona;
    }

    public void setZona(ZonaDTO zona) {
        this.zona = zona;
    }
}
