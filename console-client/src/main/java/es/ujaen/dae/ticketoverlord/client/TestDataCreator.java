package es.ujaen.dae.ticketoverlord.client;

import es.ujaen.dae.ticketoverlord.client.dtos.*;
import es.ujaen.dae.ticketoverlord.client.utilities.EventsTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.TicketsTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.UsersTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.VenuesTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class TestDataCreator {
    private final static String USER = "admin";
    private final static String PASS = "admin";

    public static void main(String[] args) {

        TestDataCreator creator = new TestDataCreator();
        creator.insertTestData();
    }

    private void insertTestData() {

        for (int i = 0; i < 2; i++) {
            try {
                deleteTickets();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            try {
                deleteUsers();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            try {
                deleteEvents();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            try {
                deleteVenues();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        insertVenues();

        insertEvents();

        insertUsers();
    }

    private void deleteTickets() {

        List<TicketDTO> tickets = TicketsTemplate.getAllTickets(USER, PASS);
        for (TicketDTO ticketDTO : tickets) {
            TicketsTemplate.deleteTicket(USER, PASS, ticketDTO);
        }
    }

    private void deleteVenues() {

        List<VenueDTO> venues = VenuesTemplate.getAllVenues(USER, PASS);
        for (VenueDTO venueDTO : venues) {
            VenuesTemplate.deleteVenue(USER, PASS, venueDTO);
        }
    }

    private void deleteEvents() {

        List<EventDTO> events = EventsTemplate.getAllEvents(USER, PASS, new HashMap<>());
        for (EventDTO eventDTO : events) {
            EventsTemplate.deleteEvent(USER, PASS, eventDTO);
        }
    }

    private void deleteUsers() {

        List<UserDTO> users = UsersTemplate.getAllUsers(USER, PASS);
        for (UserDTO userDTO : users) {
            if (!userDTO.getUsername().equalsIgnoreCase("admin")) {
                UsersTemplate.deleteUser(USER, PASS, userDTO);
            }
        }
    }

    private void insertVenues() {

        VenueDTO teatroInfanta = new VenueDTO("Teatro Infanta Leonor", "Jaén, Jaén", "Calle Molino de la Condesa, s/n");
        teatroInfanta.addZone(new ZoneDTO('A', 50));
        teatroInfanta.addZone(new ZoneDTO('B', 150));
        teatroInfanta.addZone(new ZoneDTO('C', 250));

        VenueDTO plazaToros = new VenueDTO("Plaza de Toros de Jaén", "Jaén, Jaén", "Alameda de Capuchinos S/N");
        plazaToros.addZone(new ZoneDTO('A', 1000));
        plazaToros.addZone(new ZoneDTO('B', 1000));
        plazaToros.addZone(new ZoneDTO('C', 1000));
        plazaToros.addZone(new ZoneDTO('D', 1000));

        VenueDTO cafeTeatro = new VenueDTO("Café Teatro Central", "Baeza, Jaén", "Calle Obispo Narváez 19");
        cafeTeatro.addZone(new ZoneDTO('A', 15));
        cafeTeatro.addZone(new ZoneDTO('B', 20));
        cafeTeatro.addZone(new ZoneDTO('C', 35));

        VenueDTO auditorioAlameda = new VenueDTO("Auditorio Municipal La Alameda", "Jaén, Jaén", "Alameda de Capuchinos, 14");

        VenueDTO auditorioUbeda = new VenueDTO("Auditorio Recinto Ferial de Úbeda", "Úbeda , Jaén", "Carretera de Baeza, s/n");
        auditorioUbeda.addZone(new ZoneDTO('A', 60));
        auditorioUbeda.addZone(new ZoneDTO('B', 80));

        VenuesTemplate.addVenue(USER, PASS, teatroInfanta);
        VenuesTemplate.addVenue(USER, PASS, plazaToros);
        VenuesTemplate.addVenue(USER, PASS, cafeTeatro);
        VenuesTemplate.addVenue(USER, PASS, auditorioAlameda);
        VenuesTemplate.addVenue(USER, PASS, auditorioUbeda);
    }

    private void insertEvents() {

        List<VenueDTO> venues = VenuesTemplate.getAllVenues(USER, PASS);

        Integer teatroInfantaId = null;
        Integer cafeTeatroId = null;
        Integer auditorioUbedaId = null;
        for (VenueDTO venueDTO : venues) {
            if (venueDTO.getName().equalsIgnoreCase("Teatro Infanta Leonor")) {
                teatroInfantaId = venueDTO.getVenueId();
            } else if (venueDTO.getName().equalsIgnoreCase("Café Teatro Central")) {
                cafeTeatroId = venueDTO.getVenueId();
            } else if (venueDTO.getName().equalsIgnoreCase("Auditorio Recinto Ferial de Úbeda")) {
                auditorioUbedaId = venueDTO.getVenueId();
            }
        }

        EventDTO lagoDeLosCisnes = new EventDTO("El lago de los cisnes", "Ballet", "01/01/2017", teatroInfantaId);
        lagoDeLosCisnes.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(20), 5));
        lagoDeLosCisnes.addPricePerZone(new PricePerZoneDTO('B', new BigDecimal(10), 10));
        lagoDeLosCisnes.addPricePerZone(new PricePerZoneDTO('C', new BigDecimal(5), 20));

        EventDTO nochesContrapelo = new EventDTO("Noches a contrapelo", "Comedia", "02/01/2017", teatroInfantaId);
        nochesContrapelo.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(40), 10));
        nochesContrapelo.addPricePerZone(new PricePerZoneDTO('B', new BigDecimal(20), 25));
        nochesContrapelo.addPricePerZone(new PricePerZoneDTO('C', new BigDecimal(10), 35));

        EventDTO bandoneon = new EventDTO("Al ritmo del bandoneón", "Recital", "01/01/2017", cafeTeatroId);
        bandoneon.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(50), 15));
        bandoneon.addPricePerZone(new PricePerZoneDTO('B', new BigDecimal(30), 20));
        bandoneon.addPricePerZone(new PricePerZoneDTO('C', new BigDecimal(20), 35));

        EventDTO bajoTerapia = new EventDTO("Bajo terapia", "Comedia", "01/01/2017", auditorioUbedaId);
        bajoTerapia.addPricePerZone(new PricePerZoneDTO('A',  new BigDecimal(20), 3));
        bajoTerapia.addPricePerZone(new PricePerZoneDTO('B',  new BigDecimal(40), 7));

        EventDTO quieroSerMago = new EventDTO("¡Quiero ser mago!", "Infantil", "02/01/2017", auditorioUbedaId);
        quieroSerMago.addPricePerZone(new PricePerZoneDTO('A',  new BigDecimal(10), 20));
        quieroSerMago.addPricePerZone(new PricePerZoneDTO('B',  new BigDecimal(20), 30));

        EventsTemplate.addEvent(USER, PASS, lagoDeLosCisnes);
        EventsTemplate.addEvent(USER, PASS, nochesContrapelo);
        EventsTemplate.addEvent(USER, PASS, bandoneon);
        EventsTemplate.addEvent(USER, PASS, bajoTerapia);
        EventsTemplate.addEvent(USER, PASS, quieroSerMago);
    }

    private void insertUsers() {

        UserDTO user1 = new UserDTO("Anto", "pass");
        UserDTO user2 = new UserDTO("Ele", "pass");
        UserDTO user3 = new UserDTO("Alessandro", "intrigante");
        UserDTO user4 = new UserDTO("Egle", "minkia");

        UsersTemplate.addUser(USER, PASS, user1);
        UsersTemplate.addUser(USER, PASS, user2);
        UsersTemplate.addUser(USER, PASS, user3);
        UsersTemplate.addUser(USER, PASS, user4);
    }
}
