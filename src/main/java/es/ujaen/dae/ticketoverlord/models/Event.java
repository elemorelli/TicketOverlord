package es.ujaen.dae.ticketoverlord.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private String type;
    private LocalDate date;
    private Venue venue;
    private List<PricePerZone> pricePerZones;

    public Event() {
        this.pricePerZones = new ArrayList<>();
    }

    public Event(String name, String type, LocalDate date, Venue venue, List<PricePerZone> pricePerZones) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.venue = venue;
        this.pricePerZones = pricePerZones;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", venue=" + venue +
                ", pricePerZones=" + pricePerZones +
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<PricePerZone> getPricePerZones() {
        return pricePerZones;
    }

    public void setPricePerZones(List<PricePerZone> pricePerZones) {
        this.pricePerZones = pricePerZones;
    }

    public void addPricePerZones(PricePerZone pricePerZone) {
        this.pricePerZones.add(pricePerZone);
    }
}