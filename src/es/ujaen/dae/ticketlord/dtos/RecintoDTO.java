package es.ujaen.dae.ticketlord.dtos;

import java.util.List;

public class RecintoDTO {
    private String localidad;
    private String direccion;
    private List<ZonaDTO> zonas;

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
