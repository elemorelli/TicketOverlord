package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class PricePerZoneDTO extends ResourceSupport {
    private Integer id;
    private BigDecimal price;
    private ZoneDTO zone;
    private Integer availableSeats;

    public PricePerZoneDTO() {
    }

    public PricePerZoneDTO(PricePerZone price) {
        this.id = price.getId();
        this.price = price.getPrice();
        this.zone = new ZoneDTO(price.getZone());
        this.availableSeats = price.getAvailableSeats();
    }

    @Override
    public String toString() {
        return "PricePerZoneDTO{" +
                "id=" + id +
                ", price=" + price +
                ", zone=" + zone +
                ", availableSeats=" + availableSeats +
                '}';
    }

    public Integer getPricePerZoneId() {
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
