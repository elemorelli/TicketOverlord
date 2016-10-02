package es.ujaen.dae.ticketlord.models;

import java.util.Calendar;
import java.util.List;

public class Evento {
    private String nombre;
    private String tipo;
    private Calendar fecha;
    private Recinto recinto;
    private List<PrecioPorZona> preciosPorZona;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public List<PrecioPorZona> getPreciosPorZona() {
        return preciosPorZona;
    }

    public void setPreciosPorZona(List<PrecioPorZona> preciosPorZona) {
        this.preciosPorZona = preciosPorZona;
    }
}
