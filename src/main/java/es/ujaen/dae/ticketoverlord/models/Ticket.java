package es.ujaen.dae.ticketoverlord.models;

import java.math.BigDecimal;

public class Ticket {
    private Event event;
    private Zone zone;
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(Event event, Zone zone, BigDecimal price) {
        this.event = event;
        this.zone = zone;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event=" + event +
                ", zone=" + zone +
                ", price=" + price +
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
}
