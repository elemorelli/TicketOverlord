package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.PrecioPorZona;

import java.math.BigDecimal;

public class PrecioPorZonaDTO {
    private BigDecimal precio;
    private ZonaDTO zona;

    public PrecioPorZonaDTO() {
    }

    public PrecioPorZonaDTO(PrecioPorZona precio) {
        this.precio = precio.getPrecio();
        this.zona = new ZonaDTO(precio.getZona());
    }

    @Override
    public String toString() {
        return "PrecioPorZonaDTO{" +
                "precio=" + precio +
                ", zona=" + zona +
                '}';
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public ZonaDTO getZona() {
        return zona;
    }

    public void setZona(ZonaDTO zona) {
        this.zona = zona;
    }
}
