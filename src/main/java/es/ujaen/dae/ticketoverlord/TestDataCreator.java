package es.ujaen.dae.ticketoverlord;

import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.models.User;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class TestDataCreator {
    private EventsDAO eventsDAO;
    private TicketsDAO ticketsDAO;
    private UsersDAO usersDAO;
    private VenueDAO venuesDAO;

    public static void main(String[] args) {

        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        appContext.registerShutdownHook();

        TestDataCreator creator = new TestDataCreator();

        creator.eventsDAO = (EventsDAO) appContext.getBean("EventsDAO");
        creator.ticketsDAO = (TicketsDAO) appContext.getBean("TicketsDAO");
        creator.usersDAO = (UsersDAO) appContext.getBean("UsersDAO");
        creator.venuesDAO = (VenueDAO) appContext.getBean("VenuesDAO");

        creator.insertTestData();
    }

    public void insertTestData() {

        User admin = new User("3842affe-750b-4fa1-8120-0433a21a2f74", "Admin", "admin", new ArrayList<>());
        User user1 = new User("4b955cda-215d-4937-a77c-e5140c6ed0cc", "Anto", "pass", new ArrayList<>());
        User user2 = new User("d18716ed-4f31-4ad5-9a5c-984a81873a68", "Ele", "pass", new ArrayList<>());
        User user3 = new User("1588204b-bce1-4afb-81b4-74140049c150", "Alessandro", "intrigante", new ArrayList<>());
        User user4 = new User("89b42c9a-0ad9-42c5-86a9-a89945404038", "Egle", "minkia", new ArrayList<>());

        usersDAO.insertUser(admin);
        usersDAO.insertUser(user1);
        usersDAO.insertUser(user2);
        usersDAO.insertUser(user3);
        usersDAO.insertUser(user4);

        System.out.println(usersDAO.selectUserByName(""));
    }
}
