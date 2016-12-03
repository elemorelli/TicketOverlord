package es.ujaen.dae.ticketoverlord.client.dtos;

import java.math.BigDecimal;

public class TicketDTO {
    private Integer id;
    private EventDTO event;
    private ZoneDTO zone;
    private BigDecimal price;
    private Integer quantity;

    public TicketDTO() {
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
