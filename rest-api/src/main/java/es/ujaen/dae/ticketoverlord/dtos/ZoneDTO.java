package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.ujaen.dae.ticketoverlord.models.Zone;
import es.ujaen.dae.ticketoverlord.resources.v1.VenuesResource;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ZoneDTO extends ResourceSupport {
    private Integer id;
    private Character name;
    private Integer seats;

    public ZoneDTO() {
    }

    public ZoneDTO(Character name, Integer seats) {
        this.name = name;
        this.seats = seats;
    }

    public ZoneDTO(Zone zone) {
        this.id = zone.getId();
        this.name = zone.getName();
        this.seats = zone.getSeats();

        this.add(linkTo(VenuesResource.class)
                .slash(zone.getVenue().getId())
                .slash("zones")
                .slash(this.getName()).withSelfRel());


        this.add(linkTo(VenuesResource.class)
                .slash(zone.getVenue().getId()).withSelfRel());
    }

    @Override
    public String toString() {
        return "ZoneDTO{" +
                "id=" + id +
                ", name=" + name +
                ", seats=" + seats +
                '}';
    }


    @JsonIgnore
    @JsonProperty
    public Integer getZoneId() {
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
