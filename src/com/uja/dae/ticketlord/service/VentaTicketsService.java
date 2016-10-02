package com.uja.dae.ticketlord.service;

import com.uja.dae.ticketlord.dto.EventoDTO;
import com.uja.dae.ticketlord.dto.TicketDTO;
import com.uja.dae.ticketlord.dto.UsuarioDTO;
import com.uja.dae.ticketlord.dto.ZonaDTO;

import java.util.Calendar;
import java.util.List;

public interface VentaTicketsService {
    void agregarUsuario(UsuarioDTO usuario);

    EventoDTO buscarEventoPorNombre(String nombre);

    EventoDTO buscarEventoPorNombreYLocalidad(String nombre, String localidad);

    EventoDTO buscarEventoPorFechaYTipo(Calendar fecha, String tipo);

    EventoDTO buscarEventoPorFechaTipoYLocalidad(Calendar fecha, String tipo, String localidad);

    void adquirirTicket(EventoDTO evento, ZonaDTO zona);

    List<TicketDTO> consultarTicketsAdquiridosPorUsuario(UsuarioDTO usuario);

    void crearEvento(EventoDTO evento);
}
