package es.ujaen.dae.ticketoverlord.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.ujaen.dae.ticketoverlord.models.User;
import es.ujaen.dae.ticketoverlord.resources.v1.UsersResource;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends ResourceSupport {
    private Integer userId;
    private String uuidToken;
    private String username;
    private String password;
    private String role;
    private boolean enabled;

    public UserDTO() {
    }

    public UserDTO(String uuidToken, String username, String password) {
        this.uuidToken = uuidToken;
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.uuidToken = user.getUuidToken();
        this.role = user.getRole();
        this.enabled = user.isEnabled();

        this.add(linkTo(UsersResource.class)
                .slash(this.getUsername()).withSelfRel());
        this.add(linkTo(UsersResource.class)
                .slash(this.getUsername())
                .slash("tickets").withRel("tickets"));
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", uuidToken='" + uuidToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
