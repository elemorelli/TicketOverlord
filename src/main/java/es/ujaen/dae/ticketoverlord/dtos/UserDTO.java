package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.User;

public class UserDTO {
    private String uuidToken;
    private String name;
    private String password;
    private boolean isAdmin;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.uuidToken = user.getUuidToken();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uuidToken='" + uuidToken + '\'' +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
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
