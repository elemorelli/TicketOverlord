package es.ujaen.dae.ticketlord.models;

import java.util.ArrayList;
import java.util.List;

public class Recinto {
    private String nombre;
    private String localidad;
    private String direccion;
    private List<Zona> zonas;

    public Recinto() {
        this.zonas = new ArrayList<>();
    }

    public Recinto(String nombre, String localidad, String direccion, List<Zona> zonas) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.direccion = direccion;
        this.zonas = zonas;
    }

    @Override
    public String toString() {
        return "Recinto{" +
                "localidad='" + localidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", zonas=" + zonas +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public void addZona(Zona zona) {
        this.zonas.add(zona);
    }
}
