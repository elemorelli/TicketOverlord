package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;

import java.util.ArrayList;
import java.util.List;

public class VenueDTO {
    private String name;
    private String city;
    private String address;
    private List<ZoneDTO> zones;

    public VenueDTO() {
        this.zones = new ArrayList<>();
    }

    public VenueDTO(Venue venue) {
        this.name = venue.getName();
        this.city = venue.getCity();
        this.address = venue.getAddress();
        this.zones = new ArrayList<>();
        for (Zone zone : venue.getZones()) {
            this.zones.add(new ZoneDTO(zone));
        }
    }

    public VenueDTO(String name, String city, String address, List<ZoneDTO> zones) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "VenueDTO{" +
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

    public List<ZoneDTO> getZones() {
        return zones;
    }

    public void setZones(List<ZoneDTO> zones) {
        this.zones = zones;
    }

    public void addZona(ZoneDTO zona) {
        this.zones.add(zona);
    }
}
