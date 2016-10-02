package com.uja.dae.ticketlord.dto;

import java.util.Calendar;
import java.util.List;

public class EventoDTO {
    private String nombre;
    private String tipo;
    private Calendar fecha;
    private RecintoDTO recinto;
    private List<PrecioPorZonaDTO> preciosPorZona;
}
