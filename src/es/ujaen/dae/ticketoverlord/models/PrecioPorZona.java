package es.ujaen.dae.ticketoverlord.models;

import java.math.BigDecimal;

public class PrecioPorZona {
    private BigDecimal precio;
    private Zona zona;

    public PrecioPorZona() {
    }

    public PrecioPorZona(BigDecimal precio, Zona zona) {
        this.precio = precio;
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "PrecioPorZona{" +
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
