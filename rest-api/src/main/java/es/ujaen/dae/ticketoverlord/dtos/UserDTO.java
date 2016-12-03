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

    public UserDTO(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.uuidToken = user.getUuidToken();
        this.add(linkTo(UsersResource.class)
                .slash(this.getName()).withSelfRel());
        this.add(linkTo(UsersResource.class)
                .slash(this.getName())
                .slash("tickets").withRel("tickets"));
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", uuidToken='" + uuidToken + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
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
