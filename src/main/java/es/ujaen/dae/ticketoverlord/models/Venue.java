package es.ujaen.dae.ticketoverlord.models;

import java.util.ArrayList;
import java.util.List;

public class Venue {
    private String name;
    private String city;
    private String address;
    private List<Zone> zones;

    public Venue() {
        this.zones = new ArrayList<>();
    }

    public Venue(String name, String city, String address, List<Zone> zones) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zones=" + zones +
                '}';
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

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public void addZona(Zone zone) {
        this.zones.add(zone);
    }
}
