package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Usuario;

public class UsuarioDTO {
    private String uuidToken;
    private String nombre;
    private String password;
    private boolean isAdmin;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.uuidToken = usuario.getUuidToken();
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "uuidToken='" + uuidToken + '\'' +
                ", nombre='" + nombre + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
