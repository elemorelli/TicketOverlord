package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.*;
import es.ujaen.dae.ticketoverlord.models.Evento;
import es.ujaen.dae.ticketoverlord.models.Recinto;
import es.ujaen.dae.ticketoverlord.models.Ticket;
import es.ujaen.dae.ticketoverlord.models.Usuario;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VentaTicketsServiceImpl implements VentaTicketsService {
    private List<Evento> eventos;
    private List<Ticket> tickets;
    private List<Recinto> recintos;
    private List<Usuario> usuarios;

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Recinto> getRecintos() {
        return recintos;
    }

    public void setRecintos(List<Recinto> recintos) {
        this.recintos = recintos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public void agregarUsuario(UsuarioDTO usuarioAAgregar) {

        Usuario nuevoUsuario = new Usuario(usuarioAAgregar.getNombre(), usuarioAAgregar.getPassword());
        nuevoUsuario.setUuidToken(UUID.randomUUID().toString());
        usuarios.add(nuevoUsuario);
    }

    @Override
    public Boolean existeUsuario(UsuarioDTO usuarioAComprobar) {

        for (Usuario usuario : this.usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(usuarioAComprobar.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UsuarioDTO getDatosUsuario(UsuarioDTO usuarioAObtener) {

        for (Usuario usuario : this.usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(usuarioAObtener.getNombre())) {
                UsuarioDTO usuarioLogueado = new UsuarioDTO(usuario.getNombre(), usuario.getPassword());
                if (usuarioLogueado.getNombre().equalsIgnoreCase("Administrador")) {
                    usuarioLogueado.setAdmin(true);
                }
                return usuarioLogueado;
            }
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        List<UsuarioDTO> usuarios = new ArrayList<>();
        for (Usuario usuario : this.usuarios) {
            UsuarioDTO dto = new UsuarioDTO(usuario.getUuidToken(), usuario.getNombre(), usuario.getPassword());
            usuarios.add(dto);
        }
        return usuarios;
    }

    @Override
    public List<RecintoDTO> listarRecintos() {

        List<RecintoDTO> recintos = new ArrayList<>();
        for (Recinto recinto : this.recintos) {
            recintos.add(new RecintoDTO(recinto));
        }
        return recintos;
    }

    @Override
    public List<EventoDTO> buscarEventosPorNombre(String nombre) {

        List<EventoDTO> eventosEncontrados = new ArrayList<>();
        for (Evento evento : this.eventos) {
            if (StringUtils.containsIgnoreCase(evento.getNombre(), nombre)) {
                eventosEncontrados.add(new EventoDTO(evento));
            }
        }
        return eventosEncontrados;
    }

    @Override
    public List<EventoDTO> buscarEventosPorNombreYLocalidad(String nombre, String localidad) {

        List<EventoDTO> eventosEncontrados = new ArrayList<>();
        for (Evento evento : this.eventos) {
            if (StringUtils.containsIgnoreCase(evento.getNombre(), nombre)
                    && StringUtils.containsIgnoreCase(evento.getRecinto().getLocalidad(), localidad)) {
                eventosEncontrados.add(new EventoDTO(evento));
            }
        }
        return eventosEncontrados;
    }

    @Override
    public List<EventoDTO> buscarEventosPorFechaYTipo(LocalDate fecha, String tipo) {

        List<EventoDTO> eventosEncontrados = new ArrayList<>();
        for (Evento evento : this.eventos) {
            if (evento.getFecha().equals(fecha)) {
                eventosEncontrados.add(new EventoDTO(evento));
            }
        }
        return eventosEncontrados;
    }

    @Override
    public List<EventoDTO> buscarEventosPorFechaTipoYLocalidad(LocalDate fecha, String tipo, String localidad) {

        List<EventoDTO> eventosEncontrados = new ArrayList<>();
        for (Evento evento : this.eventos) {
            if (evento.getFecha().equals(fecha)
                    && StringUtils.containsIgnoreCase(evento.getRecinto().getLocalidad(), localidad)) {
                eventosEncontrados.add(new EventoDTO(evento));
            }
        }
        return eventosEncontrados;
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
