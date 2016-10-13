package es.ujaen.dae.ticketoverlord.models;

public class Zone {
    private Character name;
    private Integer seats;

    public Zone() {
    }

    public Zone(Character name, Integer seats) {
        this.name = name;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Zone{" +
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
