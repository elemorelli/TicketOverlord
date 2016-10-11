package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Zona;

public class ZonaDTO {
    private Character nombre;
    private Integer asientos;

    public ZonaDTO() {
    }

    public ZonaDTO(Zona zona) {
        this.nombre = zona.getNombre();
        this.asientos = zona.getAsientos();
    }

    @Override
    public String toString() {
        return "ZonaDTO{" +
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
