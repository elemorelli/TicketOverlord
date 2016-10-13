package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.EventoDTO;
import es.ujaen.dae.ticketoverlord.dtos.TicketDTO;
import es.ujaen.dae.ticketoverlord.dtos.UsuarioDTO;
import es.ujaen.dae.ticketoverlord.dtos.ZonaDTO;

import java.time.LocalDate;
import java.util.List;

public interface VentaTicketsService {
    void agregarUsuario(UsuarioDTO usuario);

    Boolean existeUsuario(UsuarioDTO usuario);

    boolean autenticarUsuario(UsuarioDTO usuario);

    UsuarioDTO getDatosUsuario(UsuarioDTO usuario);

    List<EventoDTO> buscarEventosPorNombre(String nombre);

    List<EventoDTO> buscarEventosPorNombreYLocalidad(String nombre, String localidad);

    List<EventoDTO> buscarEventosPorFechaYTipo(LocalDate fecha, String tipo);

    List<EventoDTO> buscarEventosPorFechaTipoYLocalidad(LocalDate fecha, String tipo, String localidad);

    void adquirirTicket(EventoDTO evento, ZonaDTO zona);

    List<TicketDTO> consultarTicketsAdquiridosPorUsuario(UsuarioDTO usuario);

    void crearEvento(EventoDTO evento);
}
