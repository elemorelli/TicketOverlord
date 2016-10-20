package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EventDTO {
    private String name;
    private String type;
    private LocalDate date;
    private VenueDTO venue;
    private Map<Character, PricePerZoneDTO> pricesPerZone;

    public EventDTO() {
        this.pricesPerZone = new HashMap<>();
    }

    public EventDTO(Event event) {
        this.name = event.getName();
        this.type = event.getType();
        this.date = event.getDate();
        this.venue = new VenueDTO(event.getVenue());
        this.pricesPerZone = new HashMap<>();
        for (PricePerZone price : event.getPricePerZones().values()) {
            this.pricesPerZone.put(price.getZone().getName(), new PricePerZoneDTO(price));
        }
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", venue=" + venue +
                ", pricesPerZone=" + pricesPerZone +
                '}';
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
