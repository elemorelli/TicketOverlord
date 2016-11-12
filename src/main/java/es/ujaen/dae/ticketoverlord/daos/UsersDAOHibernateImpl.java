package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserUpdateException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("UsersDAO")
public class UsersDAOHibernateImpl implements UsersDAO {
    private Map<String, User> users;
    @PersistenceContext
    private EntityManager em;

    public UsersDAOHibernateImpl() {
        users = new HashMap<>();
    }

    @Override
    public User selectUserById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User selectUserByName(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            try {
                User user = em.createQuery("SELECT u FROM User u WHERE u.name = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();
                users.put(user.getName(), user);
                return user;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public List<User> selectAllUsers() {

        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public void insertUser(User user) {
        try {
            em.persist(user);
            users.put(user.getName(), user);
        } catch (Exception e) {
            // TODO: Ver de envolver las transacciones en un aspecto que translade las excepciones
            throw new UserInsertionException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            em.merge(user);
        } catch (Exception e) {
            throw new UserUpdateException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            em.remove(em.find(User.class, user.getId()));
            users.remove(user.getId());
            // TODO: Investigar porque no lo elimina así
            // em.remove(user);
        } catch (Exception e) {
            throw new UserRemovalException(e);
        }
    }
}
