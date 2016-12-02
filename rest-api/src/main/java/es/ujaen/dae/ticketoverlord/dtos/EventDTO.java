package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;
import es.ujaen.dae.ticketoverlord.resources.v1.EventsResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.AppInitializer.DATE_FORMAT;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@JsonIgnoreProperties({"venue", "pricesPerZone"})
public class EventDTO extends ResourceSupport {
    private Integer eventId;
    private String name;
    private String type;
    private String date;
    private VenueDTO venue;
    private Map<Character, PricePerZoneDTO> pricesPerZone;

    public EventDTO() {
        this.pricesPerZone = new HashMap<>();
    }

    public EventDTO(Event event) {
        this.eventId = event.getEventId();
        this.name = event.getName();
        this.type = event.getType();
        this.date = event.getDate().format(DATE_FORMAT);
        this.venue = new VenueDTO(event.getVenue());
        this.pricesPerZone = new HashMap<>();
        for (PricePerZone price : event.getPricePerZones().values()) {
            this.pricesPerZone.put(price.getZone().getName(), new PricePerZoneDTO(price));
        }

        this.add(linkTo(EventsResource.class)
                .slash(this.getEventId()).withSelfRel());
        this.add(venue.getLink(Link.REL_SELF).withRel("venue"));
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
                ", date=" + date +
                ", venue=" + venue +
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

    public VenueDTO getVenue() {
        return venue;
    }

    public void setVenue(VenueDTO venue) {
        this.venue = venue;
    }

    public Map<Character, PricePerZoneDTO> getPricesPerZone() {
        return pricesPerZone;
    }

    public void setPricesPerZone(Map<Character, PricePerZoneDTO> pricesPerZone) {
        this.pricesPerZone = pricesPerZone;
    }

    public void addPricesPerZone(PricePerZoneDTO pricePerZoneDTO) {
        this.pricesPerZone.put(pricePerZoneDTO.getZone().getName(), pricePerZoneDTO);
    }
}
