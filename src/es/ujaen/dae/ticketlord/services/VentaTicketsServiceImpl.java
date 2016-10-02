package es.ujaen.dae.ticketlord.services;

import es.ujaen.dae.ticketlord.dtos.EventoDTO;
import es.ujaen.dae.ticketlord.dtos.TicketDTO;
import es.ujaen.dae.ticketlord.dtos.UsuarioDTO;
import es.ujaen.dae.ticketlord.dtos.ZonaDTO;
import es.ujaen.dae.ticketlord.models.Evento;
import es.ujaen.dae.ticketlord.models.Recinto;
import es.ujaen.dae.ticketlord.models.Ticket;
import es.ujaen.dae.ticketlord.models.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    public void agregarUsuario(UsuarioDTO usuario) {
        usuarios.add(new Usuario(usuario.getToken(), usuario.getNombre(), usuario.getPassword(), null));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = new UsuarioDTO(usuario.getToken(), usuario.getNombre(), usuario.getPassword());
            usuariosDTO.add(dto);
        }
        return usuariosDTO;
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
