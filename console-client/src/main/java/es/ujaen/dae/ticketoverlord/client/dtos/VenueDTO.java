package es.ujaen.dae.ticketoverlord.client.dtos;

import java.util.ArrayList;
import java.util.List;

public class VenueDTO {
    private Integer venueId;
    private String name;
    private String city;
    private String address;
    private List<ZoneDTO> zones;

    public VenueDTO() {
        this.zones = new ArrayList<>();
    }

    public VenueDTO(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.zones = new ArrayList<>();
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
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

    public List<ZoneDTO> getZones() {
        return zones;
    }

    public void setZones(List<ZoneDTO> zones) {
        this.zones = zones;
    }

    public void addZone(ZoneDTO zone) {
        this.zones.add(zone);
    }
}
