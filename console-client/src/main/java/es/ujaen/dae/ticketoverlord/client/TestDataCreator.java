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

        List<TicketDTO> tickets = TicketsTemplate.getAllTickets();
        for (TicketDTO ticketDTO : tickets) {
            TicketsTemplate.deleteTicket(ticketDTO);
        }
    }

    private void deleteVenues() {

        List<VenueDTO> venues = VenuesTemplate.getAllVenues();
        for (VenueDTO venueDTO : venues) {
            VenuesTemplate.deleteVenue(venueDTO);
        }
    }

    private void deleteEvents() {

        List<EventDTO> events = EventsTemplate.getAllEvents(new HashMap<>());
        for (EventDTO eventDTO : events) {
            EventsTemplate.deleteEvent(eventDTO);
        }
    }

    private void deleteUsers() {

        List<UserDTO> users = UsersTemplate.getAllUsers();
        for (UserDTO userDTO : users) {
            if (!userDTO.getUsername().equalsIgnoreCase("admin")) {
                UsersTemplate.deleteUser(userDTO);
            }
        }
    }

    private void insertVenues() {

        VenueDTO ferial = new VenueDTO("IFEJA, Ferias Jaén", "Jaén, Jaén", "Prolongación Carretera Granada S/N");
        ferial.addZone(new ZoneDTO('A', 50));
        ferial.addZone(new ZoneDTO('B', 60));
        ferial.addZone(new ZoneDTO('C', 70));

        VenueDTO plazaToros = new VenueDTO("Plaza de Toros de Jaén", "Jaén, Jaén", "Alameda de Capuchinos S/N");
        plazaToros.addZone(new ZoneDTO('A', 100));
        plazaToros.addZone(new ZoneDTO('B', 200));
        plazaToros.addZone(new ZoneDTO('C', 300));
        plazaToros.addZone(new ZoneDTO('D', 400));

        VenueDTO centroJubilados = new VenueDTO("Centro de jubilados \"La edad de oro\"", "Úbeda, Jaén", "Calle del Sabio 23");
        centroJubilados.addZone(new ZoneDTO('A', 10));

        VenueDTO glorieta = new VenueDTO("Teatro La Glorieta", "Baeza, Jaén", "Calle del Artista 12");
        glorieta.addZone(new ZoneDTO('A', 85));
        glorieta.addZone(new ZoneDTO('B', 55));
        glorieta.addZone(new ZoneDTO('C', 35));

        VenueDTO instituto = new VenueDTO("Teatro del instituto", "Jaén, Jaén", "Calle del Estudiante 10");

        VenuesTemplate.addVenue(ferial);
        VenuesTemplate.addVenue(plazaToros);
        VenuesTemplate.addVenue(centroJubilados);
        VenuesTemplate.addVenue(glorieta);
        VenuesTemplate.addVenue(instituto);
    }

    private void insertEvents() {

        List<VenueDTO> venues = VenuesTemplate.getAllVenues();

        Integer ferialId = null;
        Integer centroJubiladosId = null;
        for (VenueDTO venueDTO : venues) {
            if (venueDTO.getName().equalsIgnoreCase("IFEJA, Ferias Jaén")) {
                ferialId = venueDTO.getVenueId();
            } else if (venueDTO.getName().equalsIgnoreCase("Centro de jubilados \"La edad de oro\"")) {
                centroJubiladosId = venueDTO.getVenueId();
            }
        }

        EventDTO feriaSanLucas = new EventDTO("Feria de San Lucas 2016", "Todo", "08/10/2016", ferialId);
        feriaSanLucas.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(20), 5));
        feriaSanLucas.addPricePerZone(new PricePerZoneDTO('B', new BigDecimal(10), 10));
        feriaSanLucas.addPricePerZone(new PricePerZoneDTO('C', new BigDecimal(5), 20));

        EventDTO nochesAndaluces = new EventDTO("Noches andaluces", "Concierto", "20/10/2016", ferialId);
        nochesAndaluces.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(40), 10));
        nochesAndaluces.addPricePerZone(new PricePerZoneDTO('B', new BigDecimal(20), 25));
        nochesAndaluces.addPricePerZone(new PricePerZoneDTO('C', new BigDecimal(10), 35));

        EventDTO bandoneon = new EventDTO("Al ritmo del bandoneón", "Concierto", "20/11/2016", centroJubiladosId);
        bandoneon.addPricePerZone(new PricePerZoneDTO('A', new BigDecimal(20), 10));

        EventsTemplate.addEvent(feriaSanLucas);
        EventsTemplate.addEvent(nochesAndaluces);
        EventsTemplate.addEvent(bandoneon);
    }

    private void insertUsers() {

        UserDTO user1 = new UserDTO("4b955cda-215d-4937-a77c-e5140c6ed0cc", "Anto", "pass");
        UserDTO user2 = new UserDTO("d18716ed-4f31-4ad5-9a5c-984a81873a68", "Ele", "pass");
        UserDTO user3 = new UserDTO("1588204b-bce1-4afb-81b4-74140049c150", "Alessandro", "intrigante");
        UserDTO user4 = new UserDTO("89b42c9a-0ad9-42c5-86a9-a89945404038", "Egle", "minkia");

        UsersTemplate.addUser(user1);
        UsersTemplate.addUser(user2);
        UsersTemplate.addUser(user3);
        UsersTemplate.addUser(user4);
    }
}
