package es.ujaen.dae.ticketoverlord;

import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.models.User;
import es.ujaen.dae.ticketoverlord.models.Venue;
import es.ujaen.dae.ticketoverlord.models.Zone;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        insertVenues();

        insertUsers();
    }

    private void insertVenues() {

        deleteVenues();

        List<Zone> zonasFerial = new ArrayList<>();
        zonasFerial.add(new Zone('A', 100));
        zonasFerial.add(new Zone('B', 200));
        zonasFerial.add(new Zone('C', 300));

        Venue ferial = new Venue("IFEJA, Ferias Jaén", "Jaén, Jaén", "Prolongación Carretera Granada S/N", zonasFerial);

        List<Zone> zonasPlazaToros = new ArrayList<>();
        zonasPlazaToros.add(new Zone('A', 100));
        zonasPlazaToros.add(new Zone('B', 200));
        zonasPlazaToros.add(new Zone('C', 300));
        zonasPlazaToros.add(new Zone('D', 400));

        Venue plazaToros = new Venue("Plaza de Toros de Jaén", "Jaén, Jaén", "Alameda de Capuchinos S/N", zonasPlazaToros);

        List<Zone> zonasCentroJubilados = new ArrayList<>();
        zonasCentroJubilados.add(new Zone('A', 10));

        Venue centroJubilados = new Venue("Centro de jubilados \"La edad de oro\"", "Úbeda, Jaén", "Calle del Sabio 23", zonasCentroJubilados);

        List<Zone> zonasGlorieta = new ArrayList<>();
        zonasGlorieta.add(new Zone('A', 100));
        zonasGlorieta.add(new Zone('B', 50));
        zonasGlorieta.add(new Zone('C', 20));

        Venue glorieta = new Venue("Teatro La Glorieta", "Baeza, Jaén", "Calle del Artista 12", zonasGlorieta);


        Venue instituto = new Venue("Teatro del instituto", "Jaén, Jaén", "Calle del Estudiante 10", new ArrayList<>());

        venuesDAO.insertVenue(ferial);
        venuesDAO.insertVenue(plazaToros);
        venuesDAO.insertVenue(centroJubilados);
        venuesDAO.insertVenue(glorieta);
        venuesDAO.insertVenue(instituto);
    }

    private void insertUsers() {
        checkAndDeleteUser("Admin");
        checkAndDeleteUser("Anto");
        checkAndDeleteUser("Ele");
        checkAndDeleteUser("Alessandro");
        checkAndDeleteUser("Egle");

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
    }

    private void checkAndDeleteUser(String username) {
        User userToCheck = usersDAO.selectUserByName(username);
        if (userToCheck != null) {
            usersDAO.delete(userToCheck);
        }
    }

    private void deleteVenues() {
        Map<String, Venue> venues = venuesDAO.selectAllVenues();
        for (Venue venue : venues.values()) {
            venuesDAO.deleteVenue(venue);
        }
    }
}
