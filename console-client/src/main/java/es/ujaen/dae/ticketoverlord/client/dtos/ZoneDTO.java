package es.ujaen.dae.ticketoverlord.client.dtos;

public class ZoneDTO {
    private Integer id;
    private Character name;
    private Integer seats;

    public ZoneDTO() {
    }

    public ZoneDTO(Character name, Integer seats) {
        this.name = name;
        this.seats = seats;
    }

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
