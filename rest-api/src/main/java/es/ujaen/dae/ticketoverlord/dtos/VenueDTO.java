package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.resources.v1.VenuesResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class VenueDTO extends ResourceSupport {
    private Integer venueId;
    private String name;
    private String city;
    private String address;
    private Map<Character, ZoneDTO> zones;

    public VenueDTO() {
        this.zones = new HashMap<>();
    }

    public VenueDTO(Venue venue) {
        this.venueId = venue.getId();
        this.name = venue.getName();
        this.city = venue.getCity();
        this.address = venue.getAddress();
        this.zones = new HashMap<>();

        for (Character character : venue.getZones().keySet()) {
            this.zones.put(character, new ZoneDTO(venue.getZones().get(character)));
        }
        this.add(linkTo(VenuesResource.class)
                .slash(this.getVenueId()).withSelfRel());
    }

    @Override
    public String toString() {
        return "VenueDTO{" +
                "venueId=" + venueId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zones=" + zones +
                '}';
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
