package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Zone;

public class ZoneDTO {
    private Integer id;
    private Character name;
    private Integer seats;

    public ZoneDTO() {
    }

    public ZoneDTO(Zone zone) {
        this.id = zone.getId();
        this.name = zone.getName();
        this.seats = zone.getSeats();
    }

    @Override
    public String toString() {
        return "ZoneDTO{" +
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
