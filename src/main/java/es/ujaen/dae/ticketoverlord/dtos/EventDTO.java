package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Event;
import es.ujaen.dae.ticketoverlord.models.PricePerZone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDTO {
    private String name;
    private String type;
    private LocalDate date;
    private VenueDTO venue;
    private List<PricePerZoneDTO> pricesPerZone;

    public EventDTO() {
        this.pricesPerZone = new ArrayList<>();
    }

    public EventDTO(Event event) {
        this.name = event.getName();
        this.type = event.getType();
        this.date = event.getDate();
        this.venue = new VenueDTO(event.getVenue());
        this.pricesPerZone = new ArrayList<>();
        for (PricePerZone price : event.getPricePerZones()) {
            this.pricesPerZone.add(new PricePerZoneDTO(price));
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

    public List<PricePerZoneDTO> getPricesPerZone() {
        return pricesPerZone;
    }

    public void setPricesPerZone(List<PricePerZoneDTO> pricesPerZone) {
        this.pricesPerZone = pricesPerZone;
    }

    public void addPricesPerZone(PricePerZoneDTO pricePerZoneDTO) {
        this.pricesPerZone.add(pricePerZoneDTO);
    }
}
