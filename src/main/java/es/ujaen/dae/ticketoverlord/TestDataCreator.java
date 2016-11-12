package es.ujaen.dae.ticketoverlord;

import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.models.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataCreator {
    private EventsDAO eventsDAO;
    private TicketsDAO ticketsDAO;
    private UsersDAO usersDAO;
    private VenueDAO venuesDAO;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

        deleteTickets();

        deleteEvents();

        deleteVenues();

        deleteUsers();

        insertVenues();

        insertEvents();

        insertUsers();
    }

    private void deleteTickets() {

        List<Ticket> tickets = ticketsDAO.selectAllTickets();
        for (Ticket ticket : tickets) {
            ticketsDAO.delete(ticket);
        }
    }

    private void deleteVenues() {

        List<Venue> venues = venuesDAO.selectAllVenues();
        for (Venue venue : venues) {
            venuesDAO.deleteVenue(venue);
        }
    }

    private void deleteEvents() {

        List<Event> events = eventsDAO.selectAllEvents();
        for (Event event : events) {
            eventsDAO.deleteEvent(event);
        }
    }

    private void deleteUsers() {

        List<User> users = usersDAO.selectAllUsers();
        for (User user : users) {
            usersDAO.deleteUser(user);
        }
    }

    private void insertVenues() {

        Map<Character, Zone> zonasFerial = new HashMap<>();
        zonasFerial.put('A', new Zone('A', 100));
        zonasFerial.put('B', new Zone('B', 200));
        zonasFerial.put('C', new Zone('C', 300));

        Venue ferial = new Venue("IFEJA, Ferias Jaén", "Jaén, Jaén", "Prolongación Carretera Granada S/N", zonasFerial);

        Map<Character, Zone> zonasPlazaToros = new HashMap<>();
        zonasPlazaToros.put('A', new Zone('A', 100));
        zonasPlazaToros.put('B', new Zone('B', 200));
        zonasPlazaToros.put('C', new Zone('C', 300));
        zonasPlazaToros.put('D', new Zone('D', 400));

        Venue plazaToros = new Venue("Plaza de Toros de Jaén", "Jaén, Jaén", "Alameda de Capuchinos S/N", zonasPlazaToros);

        Map<Character, Zone> zonasCentroJubilados = new HashMap<>();
        zonasCentroJubilados.put('A', new Zone('A', 10));

        Venue centroJubilados = new Venue("Centro de jubilados \"La edad de oro\"", "Úbeda, Jaén", "Calle del Sabio 23", zonasCentroJubilados);

        Map<Character, Zone> zonasGlorieta = new HashMap<>();
        zonasGlorieta.put('A', new Zone('A', 100));
        zonasGlorieta.put('B', new Zone('B', 50));
        zonasGlorieta.put('C', new Zone('C', 20));

        Venue glorieta = new Venue("Teatro La Glorieta", "Baeza, Jaén", "Calle del Artista 12", zonasGlorieta);

        Venue instituto = new Venue("Teatro del instituto", "Jaén, Jaén", "Calle del Estudiante 10", new HashMap<>());

        venuesDAO.insertVenue(ferial);
        venuesDAO.insertVenue(plazaToros);
        venuesDAO.insertVenue(centroJubilados);
        venuesDAO.insertVenue(glorieta);
        venuesDAO.insertVenue(instituto);
    }

    private void insertEvents() {

        Venue ferial = venuesDAO.selectVenueByName("IFEJA, Ferias Jaén");
        Venue centroJubilados = venuesDAO.selectVenueByName("Centro de jubilados \"La edad de oro\"");

        Map<Character, PricePerZone> preciosFeriaSanLucas = new HashMap<>();
        preciosFeriaSanLucas.put('A', new PricePerZone(new BigDecimal(20), 5, ferial.getZones().get('A')));
        preciosFeriaSanLucas.put('B', new PricePerZone(new BigDecimal(10), 10, ferial.getZones().get('B')));
        preciosFeriaSanLucas.put('C', new PricePerZone(new BigDecimal(5), 10, ferial.getZones().get('C')));

        Event feriaSanLucas = new Event("Feria de San Lucas 2016", "Todo", LocalDate.parse("08/10/2016", dateFormatter), ferial, preciosFeriaSanLucas);

        Event nochesAndaluces = new Event("Noches andaluces", "Concierto", LocalDate.parse("20/10/2016", dateFormatter), ferial, new HashMap<>());

        Map<Character, PricePerZone> preciosBandoneon = new HashMap<>();
        preciosBandoneon.put('A', new PricePerZone(new BigDecimal(20), 10, centroJubilados.getZones().get('A')));

        Event bandoneon = new Event("Al ritmo del bandoneón", "Concierto", LocalDate.parse("20/11/2016", dateFormatter), centroJubilados, preciosBandoneon);

        eventsDAO.insertEvent(feriaSanLucas);
        eventsDAO.insertEvent(nochesAndaluces);
        eventsDAO.insertEvent(bandoneon);
    }

    private void insertUsers() {

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
}
