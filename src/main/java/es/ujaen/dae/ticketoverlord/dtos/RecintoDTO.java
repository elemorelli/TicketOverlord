package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Recinto;
import es.ujaen.dae.ticketoverlord.models.Zona;

import java.util.ArrayList;
import java.util.List;

public class RecintoDTO {
    private String nombre;
    private String localidad;
    private String direccion;
    private List<ZonaDTO> zonas;

    public RecintoDTO() {
        this.zonas = new ArrayList<>();
    }

    public RecintoDTO(Recinto recinto) {
        this.nombre = recinto.getNombre();
        this.localidad = recinto.getLocalidad();
        this.direccion = recinto.getDireccion();
        this.zonas = new ArrayList<>();
        for (Zona zona : recinto.getZonas()) {
            this.zonas.add(new ZonaDTO(zona));
        }
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

    public void addZona(ZonaDTO zona) {
        this.zonas.add(zona);
    }
}
