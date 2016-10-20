package es.ujaen.dae.ticketoverlord.dtos;

import java.math.BigDecimal;

public class PricePerZoneDTO {
    private BigDecimal price;
    private ZoneDTO zone;
    private Integer availableSeats;

    public PricePerZoneDTO() {
    }

    public PricePerZoneDTO(es.ujaen.dae.ticketoverlord.models.PricePerZone price) {
        this.price = price.getPrice();
        this.zone = new ZoneDTO(price.getZone());
        this.availableSeats = price.getAvailableSeats();
    }

    @Override
    public String toString() {
        return "PricePerZoneDTO{" +
                "price=" + price +
                ", zone=" + zone +
                ", availableSeats=" + availableSeats +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
