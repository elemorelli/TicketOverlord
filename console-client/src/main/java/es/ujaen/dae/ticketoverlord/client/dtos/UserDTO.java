package es.ujaen.dae.ticketoverlord.client.dtos;

public class UserDTO {
    private Integer userId;
    private String uuidToken;
    private String name;
    private String password;
    private boolean isAdmin;

    public UserDTO() {
    }

    public UserDTO(String uuidToken, String name, String password) {
        this.uuidToken = uuidToken;
        this.name = name;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
