package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserUpdateException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("UsersDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsersDAOJPAImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User selectUserById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User selectUserByName(String username) {
        return em.createQuery("SELECT u FROM User u " +
                "WHERE UPPER(u.name) = UPPER(:username)", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> selectAllUsers() {

        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void insertUser(User user) {
        try {
            em.persist(user);
            em.flush();
        } catch (Exception e) {
            throw new UserInsertionException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        try {
            em.merge(user);
            em.flush();
        } catch (Exception e) {
            throw new UserUpdateException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(User user) {
        try {
            em.remove(em.find(User.class, user.getId()));
            // TODO: Investigar porque no lo elimina así
            // em.remove(user);
            em.flush();
        } catch (Exception e) {
            throw new UserRemovalException(e);
        }
    }
}
