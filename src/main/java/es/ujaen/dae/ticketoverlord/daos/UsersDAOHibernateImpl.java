package es.ujaen.dae.ticketoverlord.daos;

import es.ujaen.dae.ticketoverlord.exceptions.UserCreationException;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository("UsersDAO")
public class UsersDAOHibernateImpl implements UsersDAO {
    private List<User> users;
    //    @PersistenceContext
//    private EntityManager em;
    @Autowired
    private EntityManagerFactory emf;

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

        // TODO: Ver de donde sale la session
//        Criteria criteria = session.createCriteria(User.class);
//        criteria.add(Restrictions.eq("userName", userName));
//        List<User> users = criteria.list();

//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            User user = em.find(User.class, 1);
//            em.getTransaction().commit();
//            return user;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
    }

    @Override
    public void insertUser(User user) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            throw new UserCreationException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
