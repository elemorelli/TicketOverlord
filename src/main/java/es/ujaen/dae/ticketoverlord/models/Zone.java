package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "ZONES")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
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
