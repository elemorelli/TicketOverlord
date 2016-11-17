package es.ujaen.dae.ticketoverlord;

import es.ujaen.dae.ticketoverlord.configurations.AppConfiguration;
import es.ujaen.dae.ticketoverlord.daos.EventsDAO;
import es.ujaen.dae.ticketoverlord.daos.TicketsDAO;
import es.ujaen.dae.ticketoverlord.daos.UsersDAO;
import es.ujaen.dae.ticketoverlord.daos.VenueDAO;
import es.ujaen.dae.ticketoverlord.models.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class TestDataCreator {
    private EventsDAO eventsDAO;
    private TicketsDAO ticketsDAO;
    private UsersDAO usersDAO;
    private VenueDAO venuesDAO;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        appContext.registerShutdownHook();

        TestDataCreator creator = new TestDataCreator();

        creator.eventsDAO = (EventsDAO) appContext.getBean("EventsDAO");
        creator.ticketsDAO = (TicketsDAO) appContext.getBean("TicketsDAO");
        creator.usersDAO = (UsersDAO) appContext.getBean("UsersDAO");
        creator.venuesDAO = (VenueDAO) appContext.getBean("VenuesDAO");

        creator.insertTestData();
    }

    public void insertTestData() {

        for (int i = 0; i < 2; i++) {
            try {
                deleteTickets();
            } catch (Exception e) {
            }
            try {
                deleteUsers();
            } catch (Exception e) {
            }
            try {
                deleteEvents();
            } catch (Exception e) {
            }
            try {
                deleteVenues();
            } catch (Exception e) {
            }
        }

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

        Venue ferial = new Venue("IFEJA, Ferias Jaén", "Jaén, Jaén", "Prolongación Carretera Granada S/N", new HashMap<>());
        ferial.addZona(new Zone('A', 100));
        ferial.addZona(new Zone('B', 200));
        ferial.addZona(new Zone('C', 300));

        Venue plazaToros = new Venue("Plaza de Toros de Jaén", "Jaén, Jaén", "Alameda de Capuchinos S/N", new HashMap<>());
        plazaToros.addZona(new Zone('A', 100));
        plazaToros.addZona(new Zone('B', 200));
        plazaToros.addZona(new Zone('C', 300));
        plazaToros.addZona(new Zone('D', 400));

        Venue centroJubilados = new Venue("Centro de jubilados \"La edad de oro\"", "Úbeda, Jaén", "Calle del Sabio 23", new HashMap<>());
        centroJubilados.addZona(new Zone('A', 10));

        Venue glorieta = new Venue("Teatro La Glorieta", "Baeza, Jaén", "Calle del Artista 12", new HashMap<>());
        glorieta.addZona(new Zone('A', 100));
        glorieta.addZona(new Zone('B', 50));
        glorieta.addZona(new Zone('C', 20));

        Venue instituto = new Venue("Teatro del instituto", "Jaén, Jaén", "Calle del Estudiante 10", new HashMap<>());

        venuesDAO.insertVenue(ferial);
        venuesDAO.insertVenue(plazaToros);
        venuesDAO.insertVenue(centroJubilados);
        venuesDAO.insertVenue(glorieta);
        venuesDAO.insertVenue(instituto);
    }

    private void insertEvents() {

        List<Venue> venues = venuesDAO.selectAllVenues();

        Venue ferial = null;
        Venue centroJubilados = null;
        for (Venue venue : venues) {
            if (venue.getName().equalsIgnoreCase("IFEJA, Ferias Jaén")) {
                ferial = venue;
            } else if (venue.getName().equalsIgnoreCase("Centro de jubilados \"La edad de oro\"")) {
                centroJubilados = venue;
            }
        }

        Event feriaSanLucas = new Event("Feria de San Lucas 2016", "Todo", LocalDate.parse("08/10/2016", dateFormatter), ferial, new HashMap<>());
        feriaSanLucas.addPricePerZones(new PricePerZone(new BigDecimal(20), 5, ferial.getZones().get('A')));
        feriaSanLucas.addPricePerZones(new PricePerZone(new BigDecimal(10), 10, ferial.getZones().get('B')));
        feriaSanLucas.addPricePerZones(new PricePerZone(new BigDecimal(5), 10, ferial.getZones().get('C')));

        Event nochesAndaluces = new Event("Noches andaluces", "Concierto", LocalDate.parse("20/10/2016", dateFormatter), ferial, new HashMap<>());

        Event bandoneon = new Event("Al ritmo del bandoneón", "Concierto", LocalDate.parse("20/11/2016", dateFormatter), centroJubilados, new HashMap<>());
        bandoneon.addPricePerZones(new PricePerZone(new BigDecimal(20), 10, centroJubilados.getZones().get('A')));

        eventsDAO.insertEvent(feriaSanLucas);
        eventsDAO.insertEvent(nochesAndaluces);
        eventsDAO.insertEvent(bandoneon);
    }

    private void insertUsers() {

        User admin = new User("3842affe-750b-4fa1-8120-0433a21a2f74", "Admin", "admin");
        User user1 = new User("4b955cda-215d-4937-a77c-e5140c6ed0cc", "Anto", "pass");
        User user2 = new User("d18716ed-4f31-4ad5-9a5c-984a81873a68", "Ele", "pass");
        User user3 = new User("1588204b-bce1-4afb-81b4-74140049c150", "Alessandro", "intrigante");
        User user4 = new User("89b42c9a-0ad9-42c5-86a9-a89945404038", "Egle", "minkia");

        usersDAO.insertUser(admin);
        usersDAO.insertUser(user1);
        usersDAO.insertUser(user2);
        usersDAO.insertUser(user3);
        usersDAO.insertUser(user4);
    }
}
