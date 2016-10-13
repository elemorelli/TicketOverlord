package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.*;

import java.util.Calendar;
import java.util.List;

public interface VentaTicketsService {
    void agregarUsuario(UsuarioDTO usuarioAAgregar);

    Boolean existeUsuario(UsuarioDTO usuarioAComprobar);

    UsuarioDTO getDatosUsuario(UsuarioDTO usuarioAObtener);

    List<UsuarioDTO> listarUsuarios();

    List<RecintoDTO> listarRecintos();

    List<EventoDTO> buscarEventosPorNombre(String nombre);

    List<EventoDTO> buscarEventosPorNombreYLocalidad(String nombre, String localidad);

    List<EventoDTO> buscarEventosPorFechaYTipo(Calendar fecha, String tipo);

    List<EventoDTO> buscarEventosPorFechaTipoYLocalidad(Calendar fecha, String tipo, String localidad);

    void adquirirTicket(EventoDTO evento, ZonaDTO zona);

    List<TicketDTO> consultarTicketsAdquiridosPorUsuario(UsuarioDTO usuario);

    void crearEvento(EventoDTO evento);
}
