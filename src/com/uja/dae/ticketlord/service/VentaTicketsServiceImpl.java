package com.uja.dae.ticketlord.service;

import com.uja.dae.ticketlord.dto.EventoDTO;
import com.uja.dae.ticketlord.dto.TicketDTO;
import com.uja.dae.ticketlord.dto.UsuarioDTO;
import com.uja.dae.ticketlord.dto.ZonaDTO;
import com.uja.dae.ticketlord.models.Evento;
import com.uja.dae.ticketlord.models.Recinto;
import com.uja.dae.ticketlord.models.Ticket;
import com.uja.dae.ticketlord.models.Usuario;

import java.util.Calendar;
import java.util.List;

public class VentaTicketsServiceImpl implements VentaTicketsService {
    private List<Evento> eventos;
    private List<Ticket> tickets;
    private List<Recinto> recintos;
    private List<Usuario> usuarios;

    @Override
    public void agregarUsuario(UsuarioDTO usuario) {
    }

    @Override
    public EventoDTO buscarEventoPorNombre(String nombre) {
        return null;
    }

    @Override
    public EventoDTO buscarEventoPorNombreYLocalidad(String nombre, String localidad) {
        return null;
    }

    @Override
    public EventoDTO buscarEventoPorFechaYTipo(Calendar fecha, String tipo) {
        return null;
    }

    @Override
    public EventoDTO buscarEventoPorFechaTipoYLocalidad(Calendar fecha, String tipo, String localidad) {
        return null;
    }

    @Override
    public void adquirirTicket(EventoDTO evento, ZonaDTO zona) {
    }

    @Override
    public List<TicketDTO> consultarTicketsAdquiridosPorUsuario(UsuarioDTO usuario) {
        return null;
    }

    @Override
    public void crearEvento(EventoDTO evento) {
    }
}
