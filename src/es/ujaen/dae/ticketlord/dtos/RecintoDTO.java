package es.ujaen.dae.ticketlord.dtos;

import java.util.List;

public class RecintoDTO {
    private String nombre;
    private String localidad;
    private String direccion;
    private List<ZonaDTO> zonas;

    public RecintoDTO() {
    }

    public RecintoDTO(String nombre, String localidad, String direccion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public RecintoDTO(String nombre, String localidad, String direccion, List<ZonaDTO> zonas) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.direccion = direccion;
        this.zonas = zonas;
    }

    @Override
    public String toString() {
        return "RecintoDTO{" +
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

    public List<ZonaDTO> getZonas() {
        return zonas;
    }

    public void setZonas(List<ZonaDTO> zonas) {
        this.zonas = zonas;
    }
}
