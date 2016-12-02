package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.resources.v1.EventsResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@JsonIgnoreProperties({"pricePerZoneId", "zone"})
public class PricePerZoneDTO extends ResourceSupport {
    private Integer pricePerZoneId;
    private Character zoneName;
    private BigDecimal price;
    private ZoneDTO zone;
    private Integer availableSeats;

    public PricePerZoneDTO() {
    }

    public PricePerZoneDTO(PricePerZone price) {
        this.pricePerZoneId = price.getId();
        this.price = price.getPrice();
        this.zone = new ZoneDTO(price.getZone());
        this.zoneName = zone.getName();
        this.availableSeats = price.getAvailableSeats();

        this.add(linkTo(EventsResource.class)
                .slash(price.getEvent().getEventId())
                .slash("availability")
                .slash(this.getZone().getName()).withSelfRel());

        this.add(zone.getLink(Link.REL_SELF).withRel("zone"));
    }

    @Override
    public String toString() {
        return "PricePerZoneDTO{" +
                "pricePerZoneId=" + pricePerZoneId +
                ", price=" + price +
                ", zone=" + zone +
                ", availableSeats=" + availableSeats +
                '}';
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
