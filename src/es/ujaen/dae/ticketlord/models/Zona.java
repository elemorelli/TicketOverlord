package es.ujaen.dae.ticketlord.models;

public class Zona {
    private Character nombre;
    private Integer asientos;

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
