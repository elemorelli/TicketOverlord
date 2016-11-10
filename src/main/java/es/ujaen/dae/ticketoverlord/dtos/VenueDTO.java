package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Venue;

import java.util.HashMap;
import java.util.Map;

public class VenueDTO {
    private String name;
    private String city;
    private String address;
    private Map<Character, ZoneDTO> zones;

    public VenueDTO() {
        this.zones = new HashMap<>();
    }

    public VenueDTO(Venue venue) {
        this.name = venue.getName();
        this.city = venue.getCity();
        this.address = venue.getAddress();
        this.zones = new HashMap<>();

        for (Character character : venue.getZones().keySet()) {
            this.zones.put(character, new ZoneDTO(venue.getZones().get(character)));
        }
    }

    public VenueDTO(String name, String city, String address, HashMap<Character, ZoneDTO> zones) {
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

    public Map<Character, ZoneDTO> getZones() {
        return zones;
    }

    public void setZones(Map<Character, ZoneDTO> zones) {
        this.zones = zones;
    }

    public void addZona(ZoneDTO zone) {
        this.zones.put(zone.getName(), zone);
    }
}
