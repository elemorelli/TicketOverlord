package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "VENUES")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String city;
    private String address;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Map<Character, Zone> zones;

    public Venue() {
        this.zones = new HashMap<>();
    }

    public Venue(String name, String city, String address, Map<Character, Zone> zones) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
//                ", zones=" + zones +
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Character, Zone> getZones() {
        return zones;
    }

    public void setZones(Map<Character, Zone> zones) {
        this.zones = zones;
    }

    public void addZona(Zone zone) {
        this.zones.put(zone.getName(), zone);
    }
}
