package es.ujaen.dae.ticketoverlord.models;

import java.math.BigDecimal;

public class Ticket {
    private Event event;
    private Zone zone;
    private BigDecimal price;
    private Integer quantity;

    @Override
    public String toString() {
        return "Ticket{" +
                "event=" + event +
                ", zone=" + zone +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
