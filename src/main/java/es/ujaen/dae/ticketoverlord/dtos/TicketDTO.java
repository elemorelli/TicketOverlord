package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Ticket;

import java.math.BigDecimal;

public class TicketDTO {
    private BigDecimal precio;
    private EventoDTO evento;
    private ZonaDTO zona;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.precio = ticket.getPrecio();
        this.evento = new EventoDTO(ticket.getEvento());
        this.zona = new ZonaDTO(ticket.getZona());
    }

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
