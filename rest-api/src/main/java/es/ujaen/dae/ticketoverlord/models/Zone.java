package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;

@Entity
@Table(name = "ZONES")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENUE_ID")
    private Venue venue;
    private Character name;
    private Integer seats;

    public Zone() {
    }

    public Zone(Character name, Integer seats) {
        this.name = name;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", venueId=" + venue.getId() +
                ", name=" + name +
                ", seats=" + seats +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
