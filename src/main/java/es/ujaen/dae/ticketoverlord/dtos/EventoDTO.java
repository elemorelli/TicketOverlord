package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Evento;
import es.ujaen.dae.ticketoverlord.models.PrecioPorZona;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventoDTO {
    private String nombre;
    private String tipo;
    private Calendar fecha;
    private RecintoDTO recinto;
    private List<PrecioPorZonaDTO> preciosPorZona;

    public EventoDTO() {
        this.preciosPorZona = new ArrayList<>();
    }

    public EventoDTO(Evento evento) {
        this.nombre = evento.getNombre();
        this.tipo = evento.getTipo();
        this.fecha = evento.getFecha();
        this.recinto = new RecintoDTO(evento.getRecinto());
        this.preciosPorZona = new ArrayList<>();
        for (PrecioPorZona precio : evento.getPreciosPorZona()) {
            this.preciosPorZona.add(new PrecioPorZonaDTO(precio));
        }
    }

    public EventoDTO(String nombre, String tipo, Calendar fecha, RecintoDTO recinto, List<PrecioPorZonaDTO> preciosPorZona) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.recinto = recinto;
        this.preciosPorZona = preciosPorZona;
    }

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

    public void addPrecioPorZona(PrecioPorZonaDTO precioPorZona) {
        this.preciosPorZona.add(precioPorZona);
    }
}
