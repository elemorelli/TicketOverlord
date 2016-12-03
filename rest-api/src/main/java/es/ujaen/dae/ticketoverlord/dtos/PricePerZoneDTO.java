package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.resources.v1.EventsResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class PricePerZoneDTO extends ResourceSupport {
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

    public PricePerZoneDTO(PricePerZone price) {
        this.pricePerZoneId = price.getId();
        this.zoneName = price.getZone().getName();
        this.price = price.getPrice();
        this.availableSeats = price.getAvailableSeats();

        this.add(linkTo(EventsResource.class)
                .slash(price.getEvent().getEventId())
                .slash("availability")
                .slash(this.getZoneName()).withSelfRel());

        this.add(new ZoneDTO(price.getZone()).getLink(Link.REL_SELF).withRel("zone"));
    }

    @Override
    public String toString() {
        return "PricePerZoneDTO{" +
                "pricePerZoneId=" + pricePerZoneId +
                ", price=" + price +
                ", zoneName=" + zoneName +
                ", availableSeats=" + availableSeats +
                '}';
    }

    @JsonIgnore
    @JsonProperty
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
