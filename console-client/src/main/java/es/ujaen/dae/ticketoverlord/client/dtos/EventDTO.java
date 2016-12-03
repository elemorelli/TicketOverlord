package es.ujaen.dae.ticketoverlord.client.dtos;

import java.util.ArrayList;
import java.util.List;

public class EventDTO {
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
