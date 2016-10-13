package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Zone;

public class ZoneDTO {
    private Character name;
    private Integer seats;

    public ZoneDTO() {
    }

    public ZoneDTO(Zone zone) {
        this.name = zone.getName();
        this.seats = zone.getSeats();
    }

    @Override
    public String toString() {
        return "ZoneDTO{" +
                "name=" + name +
                ", seats=" + seats +
                '}';
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
