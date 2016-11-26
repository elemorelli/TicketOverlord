package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Ticket;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class TicketDTO extends ResourceSupport {
    private Integer id;
    private EventDTO event;
    private ZoneDTO zone;
    private BigDecimal price;
    private Integer quantity;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.event = new EventDTO(ticket.getEvent());
        this.zone = new ZoneDTO(ticket.getZone());
        this.quantity = ticket.getQuantity();
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", event=" + event +
                ", zone=" + zone +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getTicketId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
