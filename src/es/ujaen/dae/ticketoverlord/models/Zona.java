package es.ujaen.dae.ticketoverlord.models;

public class Zona {
    private Character nombre;
    private Integer asientos;

    public Zona() {
    }

    public Zona(Character nombre, Integer asientos) {
        this.nombre = nombre;
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "nombre=" + nombre +
                ", asientos=" + asientos +
                '}';
    }

    public Character getNombre() {
        return nombre;
    }

    public void setNombre(Character nombre) {
        this.nombre = nombre;
    }

    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }
}
