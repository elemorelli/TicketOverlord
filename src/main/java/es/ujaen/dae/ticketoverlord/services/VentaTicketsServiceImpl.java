package es.ujaen.dae.ticketoverlord.services;

import es.ujaen.dae.ticketoverlord.dtos.*;
import es.ujaen.dae.ticketoverlord.models.*;

import java.util.ArrayList;
import java.util.Calendar;
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
            if (usuario.getNombre().equals(usuarioAComprobar.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UsuarioDTO getDatosUsuario(UsuarioDTO usuarioAObtener) {

        for (Usuario usuario : this.usuarios) {
            if (usuario.getNombre().equals(usuarioAObtener.getNombre())) {
                return new UsuarioDTO(usuario.getNombre(), usuario.getPassword());
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
            RecintoDTO dto = new RecintoDTO(recinto.getNombre(), recinto.getLocalidad(), recinto.getDireccion());
            for (Zona zona : recinto.getZonas()) {
                ZonaDTO dtoZona = new ZonaDTO(zona.getNombre(), zona.getAsientos());
                dto.addZona(dtoZona);
            }
            recintos.add(dto);
        }
        return recintos;
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
