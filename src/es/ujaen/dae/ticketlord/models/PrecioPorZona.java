package es.ujaen.dae.ticketlord.models;

import java.math.BigDecimal;

public class PrecioPorZona {
    private BigDecimal precio;
    private Zona zona;

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
