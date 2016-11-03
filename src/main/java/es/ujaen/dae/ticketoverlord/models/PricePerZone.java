package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class PricePerZone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private BigDecimal price;
    @ManyToOne
    private Zone zone;
    private Integer availableSeats;

    public PricePerZone() {
    }

    public PricePerZone(BigDecimal price, Zone zone) {
        this.price = price;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "PricePerZone{" +
                "id=" + id +
                ", price=" + price +
                ", zone=" + zone +
                ", availableSeats=" + availableSeats +
                '}';
    }

    public Integer getId() {
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

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
