package es.ujaen.dae.ticketoverlord.dtos;

public class UsuarioDTO {
    private String uuidToken;
    private String nombre;
    private String password;
    private boolean isAdmin;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public UsuarioDTO(String uuidToken, String nombre, String password) {
        this.uuidToken = uuidToken;
        this.nombre = nombre;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "uuidToken='" + uuidToken + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
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
