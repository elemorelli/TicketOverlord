package es.ujaen.dae.ticketlord.models;

import java.util.Calendar;
import java.util.List;

public class Evento {
    private String nombre;
    private String tipo;
    private Calendar fecha;
    private Recinto recinto;
    private List<PrecioPorZona> preciosPorZona;
}
