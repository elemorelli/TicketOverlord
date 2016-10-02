package es.ujaen.dae.ticketlord.dtos;

import java.util.Calendar;
import java.util.List;

public class EventoDTO {
    private String nombre;
    private String tipo;
    private Calendar fecha;
    private RecintoDTO recinto;
    private List<PrecioPorZonaDTO> preciosPorZona;

    @Override
    public String toString() {
        return "EventoDTO{" +
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

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public RecintoDTO getRecinto() {
        return recinto;
    }

    public void setRecinto(RecintoDTO recinto) {
        this.recinto = recinto;
    }

    public List<PrecioPorZonaDTO> getPreciosPorZona() {
        return preciosPorZona;
    }

    public void setPreciosPorZona(List<PrecioPorZonaDTO> preciosPorZona) {
        this.preciosPorZona = preciosPorZona;
    }
}
