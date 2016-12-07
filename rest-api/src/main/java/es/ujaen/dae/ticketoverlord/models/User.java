package es.ujaen.dae.ticketoverlord.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String uuidToken;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;
    private String role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = ROLE_USER;
    }

    public User(String uuidToken, String username, String password) {
        this.uuidToken = uuidToken;
        this.username = username;
        this.password = password;
        this.role = ROLE_USER;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuidToken='" + uuidToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equals(ROLE_USER) || role.equals(ROLE_ADMIN))
            this.role = role;
        else throw new RuntimeException("Invalid user role");
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
