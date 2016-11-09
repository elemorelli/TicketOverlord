package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository("UsersDAO")
public class UsersDAOHibernateImpl implements UsersDAO {
    private List<User> users;
    @PersistenceContext
    private EntityManager em;

    public UsersDAOHibernateImpl() {
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

        em.persist(user);
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
