package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
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
    public User selectUserByName(String userName) {
        if (users.containsKey(userName)) {
            return users.get(userName);
        } else {
            try {
                return em.createQuery("SELECT u FROM User u where u.name = :username", User.class)
                        .setParameter("username", userName)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    @Override
    public void insertUser(User user) {
        em.persist(user);
        users.put(user.getName(), user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(em.merge(user));
    }
}
