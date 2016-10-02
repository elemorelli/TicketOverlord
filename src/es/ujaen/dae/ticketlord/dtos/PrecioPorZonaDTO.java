package es.ujaen.dae.ticketlord.dtos;

import java.math.BigDecimal;

public class PrecioPorZonaDTO {
    private BigDecimal precio;
    private ZonaDTO zona;

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
