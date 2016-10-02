package es.ujaen.dae.ticketlord.services;

import es.ujaen.dae.ticketlord.dtos.*;

import java.util.Calendar;
import java.util.List;

public interface VentaTicketsService {
    void agregarUsuario(UsuarioDTO usuario);

    List<UsuarioDTO> listarUsuarios();

    List<RecintoDTO> listarRecintos();

    EventoDTO buscarEventoPorNombre(String nombre);

    EventoDTO buscarEventoPorNombreYLocalidad(String nombre, String localidad);

    EventoDTO buscarEventoPorFechaYTipo(Calendar fecha, String tipo);

    EventoDTO buscarEventoPorFechaTipoYLocalidad(Calendar fecha, String tipo, String localidad);

    void adquirirTicket(EventoDTO evento, ZonaDTO zona);

    List<TicketDTO> consultarTicketsAdquiridosPorUsuario(UsuarioDTO usuario);

    void crearEvento(EventoDTO evento);
}
