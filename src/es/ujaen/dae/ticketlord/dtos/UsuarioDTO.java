package es.ujaen.dae.ticketlord.dtos;

public class UsuarioDTO {
    private Long token;
    private String nombre;
    private String password;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long token, String nombre, String password) {
        this.token = token;
        this.nombre = nombre;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "token=" + token +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
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
}
