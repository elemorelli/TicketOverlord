package es.ujaen.dae.ticketoverlord.models;

import java.math.BigDecimal;

public class PricePerZone {
    private BigDecimal price;
    private Zone zone;

    public PricePerZone() {
    }

    public PricePerZone(BigDecimal price, Zone zone) {
        this.price = price;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "PricePerZoneDTO{" +
                "price=" + price +
                ", zone=" + zone +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}