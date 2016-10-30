package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDAOBasicImpl implements UsersDAO {
    @Resource(name = "usuariosTestData")
    private List<User> users;

    public UsersDAOBasicImpl() {
        users = new ArrayList<>();
    }

    @Override
    public User selectUserByName(String userName) {

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        // TODO
    }

    @Override
    public void delete(User user) {
        // TODO
    }
}
