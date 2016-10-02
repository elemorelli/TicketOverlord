package es.ujaen.dae.ticketlord.models;

import java.math.BigDecimal;

public class PrecioPorZona {
    private BigDecimal precio;
    private Zona zona;

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
