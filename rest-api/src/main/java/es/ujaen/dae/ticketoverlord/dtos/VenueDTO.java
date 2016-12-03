package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.resources.v1.VenuesResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class VenueDTO extends ResourceSupport {
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

    public VenueDTO(Venue venue) {
        this.venueId = venue.getId();
        this.name = venue.getName();
        this.city = venue.getCity();
        this.address = venue.getAddress();
        this.zones = new ArrayList<>();

        for (Character character : venue.getZones().keySet()) {
            this.zones.add(new ZoneDTO(venue.getZones().get(character)));
        }
        this.add(linkTo(VenuesResource.class)
                .slash(this.getVenueId()).withSelfRel());
        this.add(linkTo(VenuesResource.class)
                .slash(this.getVenueId())
                .slash("zones").withRel("zones"));
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

    @JsonIgnore
    @JsonProperty
    public List<ZoneDTO> getZones() {
        return zones;
    }

    public void setZones(List<ZoneDTO> zones) {
        this.zones = zones;
    }

    public void addZone(ZoneDTO zone) {
        this.zones.add(zone);
    }

    public ZoneDTO getZone(Character zoneName) {
        for (ZoneDTO dto : zones) {
            if (dto.getName().equals(zoneName))
                return dto;
        }
        return null;
    }
}
