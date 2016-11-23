package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserInsertionException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserRemovalException;
import es.ujaen.dae.ticketoverlord.exceptions.dao.users.UserUpdateException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("UsersDAO")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsersDAOHibernateImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Cacheable(value = "usersCache")
    public User selectUserById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Cacheable(value = "usersCache")
    public User selectUserByName(String username) {
        try {
            User user = em.createQuery("SELECT u FROM User u " +
                    "WHERE UPPER(u.name) = UPPER(:username)", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Cacheable(value = "usersCache")
    public List<User> selectAllUsers() {

        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Caching(evict = {
            @CacheEvict(value = "usersCache", key = "0"),
            @CacheEvict(value = "usersCache", key = "#user.getName()")
    })
    public void insertUser(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            // TODO: Ver de envolver las transacciones en un aspecto que translade las excepciones
            throw new UserInsertionException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @Caching(evict = {
            @CacheEvict(value = "usersCache", key = "0"),
            @CacheEvict(value = "usersCache", key = "#user.getId()"),
            @CacheEvict(value = "usersCache", key = "#user.getName()")
    })
    public void updateUser(User user) {
        try {
            em.merge(user);
        } catch (Exception e) {
            throw new UserUpdateException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @Caching(evict = {
            @CacheEvict(value = "usersCache", key = "0"),
            @CacheEvict(value = "usersCache", key = "#user.getId()"),
            @CacheEvict(value = "usersCache", key = "#user.getName()")
    })
    public void deleteUser(User user) {
        try {
            em.remove(em.find(User.class, user.getId()));
            // TODO: Investigar porque no lo elimina así
            // em.remove(user);
        } catch (Exception e) {
            throw new UserRemovalException(e);
        }
    }
}
