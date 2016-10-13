package es.ujaen.dae.ticketoverlord.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nombre;
    private String tipo;
    private LocalDate fecha;
    private Recinto recinto;
    private List<PrecioPorZona> preciosPorZona;

    public Evento() {
        this.preciosPorZona = new ArrayList<>();
    }

    public Evento(String nombre, String tipo, LocalDate fecha, Recinto recinto, List<PrecioPorZona> preciosPorZona) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.recinto = recinto;
        this.preciosPorZona = preciosPorZona;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fecha=" + fecha +
                ", recinto=" + recinto +
                ", preciosPorZona=" + preciosPorZona +
                '}';
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public void addPrecioPorZona(PrecioPorZona precioPorZona) {
        this.preciosPorZona.add(precioPorZona);
    }
}
