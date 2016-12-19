package es.ujaen.dae.ticketoverlord.client.dtos;

public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public UserDTO() {
        this.role = ROLE_USER;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = ROLE_USER;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return this.role.equals(ROLE_ADMIN);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
