package es.ujaen.dae.ticketoverlord.client.dtos;

import java.math.BigDecimal;

public class PricePerZoneDTO {
    private Integer pricePerZoneId;
    private Character zoneName;
    private BigDecimal price;
    private Integer availableSeats;

    public PricePerZoneDTO() {
    }

    public PricePerZoneDTO(Character zoneName, BigDecimal price, Integer availableSeats) {
        this.zoneName = zoneName;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public Integer getPricePerZoneId() {
        return pricePerZoneId;
    }

    public void setPricePerZoneId(Integer pricePerZoneId) {
        this.pricePerZoneId = pricePerZoneId;
    }

    public Character getZoneName() {
        return zoneName;
    }

    public void setZoneName(Character zoneName) {
        this.zoneName = zoneName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
