package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.resources.v1.EventsResource;
import es.ujaen.dae.ticketoverlord.resources.v1.VenuesResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

import static es.ujaen.dae.ticketoverlord.configurations.AppInitializer.DATE_FORMAT;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class EventDTO extends ResourceSupport {
    private Integer eventId;
    private String name;
    private String type;
    private String date;
    private Integer venueId;
    private List<PricePerZoneDTO> pricesPerZone;

    public EventDTO() {
        this.pricesPerZone = new ArrayList<>();
    }

    public EventDTO(String name, String type, String date, Integer venueId) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.venueId = venueId;
        this.pricesPerZone = new ArrayList<>();
    }

    public EventDTO(Event event) {
        this.eventId = event.getId();
        this.name = event.getName();
        this.type = event.getType();
        this.date = event.getDate().format(DATE_FORMAT);
        this.venueId = event.getVenue().getId();
        this.pricesPerZone = new ArrayList<>();
        for (PricePerZone price : event.getPricePerZones().values()) {
            this.pricesPerZone.add(new PricePerZoneDTO(price));
        }

        this.add(linkTo(EventsResource.class)
                .slash(this.getEventId()).withSelfRel());
        this.add(linkTo(VenuesResource.class)
                .slash(this.getVenueId()).withRel("venue"));
        this.add(linkTo(EventsResource.class)
                .slash(this.getEventId())
                .slash("availability").withRel("availability"));
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", venueId=" + venueId +
                ", pricesPerZone=" + pricesPerZone +
                '}';
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    @JsonIgnore
    @JsonProperty
    public List<PricePerZoneDTO> getPricesPerZone() {
        return pricesPerZone;
    }

    public void setPricesPerZone(List<PricePerZoneDTO> pricesPerZone) {
        this.pricesPerZone = pricesPerZone;
    }

    public void addPricePerZone(PricePerZoneDTO pricePerZoneDTO) {
        this.pricesPerZone.add(pricePerZoneDTO);
    }

    public PricePerZoneDTO getPricePerZone(Character zoneName) {
        for (PricePerZoneDTO dto : pricesPerZone) {
            if (dto.getZoneName().equals(zoneName))
                return dto;
        }
        return null;
    }
}
