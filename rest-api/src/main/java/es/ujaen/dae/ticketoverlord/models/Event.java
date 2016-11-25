package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private LocalDate date;
    @ManyToOne
    private Venue venue;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Character, PricePerZone> pricePerZones;

    public Event() {
        this.pricePerZones = new HashMap<>();
    }

    public Event(String name, String type, LocalDate date, Venue venue, Map<Character, PricePerZone> pricePerZones) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.venue = venue;
        this.pricePerZones = pricePerZones;
        for (Character key : pricePerZones.keySet()) {
            this.pricePerZones.get(key).setEvent(this);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", venue=" + venue +
                ", pricePerZones=" + pricePerZones +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Map<Character, PricePerZone> getPricePerZones() {
        return pricePerZones;
    }

    public void setPricePerZones(Map<Character, PricePerZone> pricePerZones) {
        this.pricePerZones = pricePerZones;
    }

    public void addPricePerZones(PricePerZone pricePerZone) {
        pricePerZone.setEvent(this);
        this.pricePerZones.put(pricePerZone.getZone().getName(), pricePerZone);
    }
}
