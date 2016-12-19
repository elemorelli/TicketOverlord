package es.ujaen.dae.ticketoverlord.client;

import es.ujaen.dae.ticketoverlord.client.dtos.*;
import es.ujaen.dae.ticketoverlord.client.utilities.EventsTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.TicketsTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.UsersTemplate;
import es.ujaen.dae.ticketoverlord.client.utilities.VenuesTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleClient {
    private final InputStreamReader isr = new InputStreamReader(System.in);
    private final BufferedReader br = new BufferedReader(isr);
    private final static List<String> affirmatives = Arrays.asList("S", "SI", "SÍ", "Y", "YES");
    private final static List<String> negatives = Arrays.asList("N", "NO");
    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private UserDTO authenticatedUser = null;

    public static void main(String[] args) {
        new ConsoleClient().mainLoop();
    }

    private void mainLoop() {
        int op;
        do {
            op = printMainMenu();
            parseMainMenuOption(op);
        } while (op != 0);
    }

    private int printMainMenu() {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("- Elija una opción:               -");
        System.out.println("- 1.- Registrarse                 -");
        System.out.println("- 2.- Login                       -");
        System.out.println("- 0.- Salir                       -");
        System.out.println("-----------------------------------");
        return readNumber();
    }

    private void parseMainMenuOption(int op) {

        switch (op) {
            case 1:
                registerUser();
                break;
            case 2:
                authenticateUser();
                if (authenticatedUser != null) do {
                    if (authenticatedUser.isAdmin()) {
                        op = printAdminMenu();
                        parseAdminMenuOption(op);
                    } else {
                        op = printUserMenu();
                        printUserMenuOption(op);
                    }
                } while (op != 0);
                break;
            case 0:
                System.out.println("Gracias por utilizar el sistema TicketOverlord. ¡Hasta pronto!");
                break;
            default:
                System.err.println("Opción inválida");
                break;
        }
    }

    private int printUserMenu() {

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido " + authenticatedUser.getUsername() + ". Elija una opción:");
        System.out.println("1.- Buscar eventos por nombre");
        System.out.println("2.- Buscar eventos por nombre y localidad");
        System.out.println("3.- Buscar eventos por fecha y tipo de evento");
        System.out.println("4.- Buscar eventos por fecha, tipo de evento y localidad");
        System.out.println("5.- Consultar Tickets adquiridos");
        System.out.println("0.- Logout");
        System.out.println("-----------------------------------");
        return readNumber();
    }

    private void printUserMenuOption(int op) {

        switch (op) {
            case 1:
                findEventsByName();
                break;
            case 2:
                findEventsByNameAndCity();
                break;
            case 3:
                findEventsByDateAndType();
                break;
            case 4:
                findEventsByDateTypeAndCity();
                break;
            case 5:
                listTickets();
                break;
            case 0:
                logout();
                break;
            default:
                System.err.println("Opción inválida");
        }
    }

    private void findEventsByName() {

        System.out.println("Ingrese el nombre del evento:");
        String eventName = readText();

        Map<String, String> filters = new HashMap<>();
        filters.put("name", eventName);
        List<EventDTO> events = EventsTemplate.getAllEvents(authenticatedUser.getUsername(), authenticatedUser.getPassword(), filters);

        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre");
        }
    }

    private void findEventsByNameAndCity() {

        System.out.println("Ingrese el nombre del evento:");
        String eventName = readText();
        System.out.println("Ingrese la localidad");
        String eventCity = readText();

        Map<String, String> filters = new HashMap<>();
        filters.put("name", eventName);
        filters.put("city", eventCity);
        List<EventDTO> events = EventsTemplate.getAllEvents(authenticatedUser.getUsername(), authenticatedUser.getPassword(), filters);

        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre en esa localidad");
        }
    }

    private void findEventsByDateAndType() {

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        String eventDate = readDate();
        System.out.println("Ingrese el tipo de evento:");
        String eventType = readText();

        Map<String, String> filters = new HashMap<>();
        filters.put("date", eventDate);
        filters.put("type", eventType);
        List<EventDTO> events = EventsTemplate.getAllEvents(authenticatedUser.getUsername(), authenticatedUser.getPassword(), filters);

        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos de ese tipo en esa fecha");
        }
    }

    private void findEventsByDateTypeAndCity() {

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        String eventDate = readDate();
        System.out.println("Ingrese el tipo de evento:");
        String eventType = readText();
        System.out.println("Ingrese la localidad");
        String eventCity = readText();

        Map<String, String> filters = new HashMap<>();
        filters.put("date", eventDate);
        filters.put("type", eventType);
        filters.put("city", eventCity);
        List<EventDTO> events = EventsTemplate.getAllEvents(authenticatedUser.getUsername(), authenticatedUser.getPassword(), filters);

        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos de ese tipo en esa fecha y en esa localidad");
        }
    }

    private void printEventList(List<EventDTO> events) {

        System.out.println("Eventos encontrados:");
        int eventNumber = 0;
        for (EventDTO event : events) {

            VenueDTO venue = VenuesTemplate.getVenue(authenticatedUser.getUsername(), authenticatedUser.getPassword(), event.getVenueId());

            System.out.println();
            System.out.println("  EVENTO " + ++eventNumber + ": \"" + event.getName() + "\"");
            System.out.println("    Tipo: " + event.getType());
            System.out.println("    Fecha: " + event.getDate());
            System.out.println("    Recinto: " + venue.getName());
            System.out.println("    Localidad: " + venue.getCity());

            List<PricePerZoneDTO> pricesPerZone = EventsTemplate.getEventAvailability(authenticatedUser.getUsername(), authenticatedUser.getPassword(), event.getEventId());
            if (!pricesPerZone.isEmpty()) {
                System.out.println("    Precios:");

                for (PricePerZoneDTO pricePerZoneDTO : pricesPerZone) {
                    System.out.print("      Zone '" + pricePerZoneDTO.getZoneName()
                            + "' - €" + pricePerZoneDTO.getPrice());
                    if (pricePerZoneDTO.getAvailableSeats() > 0) {
                        System.out.println(" (" + pricePerZoneDTO.getAvailableSeats() + " tickets disponibles)");
                    } else {
                        System.out.println(" ENTRADAS AGOTADAS");
                    }
                }
            } else {
                System.out.println("    Todavía no se ha asignado los precios");
            }
        }

        buyTicketsFromList(events);
    }

    private void buyTicketsFromList(List<EventDTO> events) {

        System.out.println();
        if (events.size() > 1) {
            System.out.println("¿Desea comprar tickets para uno de estos eventos? S/N");
        } else {
            System.out.println("¿Desea comprar tickets para este evento? S/N");
        }
        String input;
        do {
            input = readText().toUpperCase();
        } while (!affirmatives.contains(input) && !negatives.contains(input));

        if (affirmatives.contains(input)) {

            int eventNumber = 1;
            if (events.size() > 1) {
                System.out.println("Ingrese el número del evento para el cual desea comprar tickets");
                do {
                    eventNumber = readNumber();
                } while (eventNumber < 1 || eventNumber > events.size());
            }

            EventDTO event = events.get(eventNumber - 1);

            Set<Character> zoneNames = new HashSet<>();
            List<PricePerZoneDTO> prices = EventsTemplate.getEventAvailability(authenticatedUser.getUsername(), authenticatedUser.getPassword(), event.getEventId());
            if (prices != null && !prices.isEmpty()) {
                System.out.println("Seleccione zona a la cual desea asistir:");

                for (PricePerZoneDTO pricePerZoneDTO : prices) {
                    zoneNames.add(pricePerZoneDTO.getZoneName());
                    System.out.print("Zona '" + pricePerZoneDTO.getZoneName()
                            + "' a €" + pricePerZoneDTO.getPrice());
                    if (pricePerZoneDTO.getAvailableSeats() > 0) {
                        System.out.println(" (" + pricePerZoneDTO.getAvailableSeats() + " tickets disponibles)");
                    } else {
                        System.out.println(" ENTRADAS AGOTADAS");
                    }
                }

                Character selectedZone;
                PricePerZoneDTO selectedPrice = null;
                do {
                    selectedZone = readCharacter();
                    if (!zoneNames.contains(selectedZone)) {
                        System.err.println("La zona ingresada no existe");
                    } else {
                        for (PricePerZoneDTO price : prices) {
                            if (price.getZoneName().equals(selectedZone)) {
                                selectedPrice = price;
                                break;
                            }
                        }
                        if (selectedPrice.getAvailableSeats() <= 0) {
                            System.err.println("TICKETS AGOTADOS PARA LA ZONA '" + selectedZone + "'");
                        }
                    }
                }
                while (!zoneNames.contains(selectedZone) || selectedPrice.getAvailableSeats() <= 0);

                Integer ticketsToBuy;
                do {
                    System.out.println("Ingrese la cantidad de tickets (" + selectedPrice.getAvailableSeats() + " tickets disponibles):");
                    ticketsToBuy = readNumber();
                } while (ticketsToBuy <= 0 || ticketsToBuy > selectedPrice.getAvailableSeats());

                VenueDTO venue = VenuesTemplate.getVenue(authenticatedUser.getUsername(), authenticatedUser.getPassword(), event.getVenueId());

                System.out.println();
                System.out.println("-----------------------------------");
                System.out.println("Resumen de la compra:");
                System.out.println("  Evento: " + event.getName());
                System.out.println("  Fecha: " + event.getDate());
                System.out.println("  Recinto: " + venue.getName());
                System.out.println("  Zona: " + selectedPrice.getZoneName());
                System.out.println("  Precio por ticket: €" + selectedPrice.getPrice());
                System.out.println("  Cantidad de tickets: " + ticketsToBuy);
                System.out.println("  Se le facturará un total de €" + (selectedPrice.getPrice().multiply(new BigDecimal(ticketsToBuy))));
                System.out.println("-----------------------------------");
                System.out.println();
                System.out.println("  ¿Desea confirmar la operación? S/N");

                do {
                    input = readText().toUpperCase();
                } while (!affirmatives.contains(input) && !negatives.contains(input));

                if (affirmatives.contains(input)) {
                    //try {
                    TicketDTO ticket = new TicketDTO();
                    ticket.setUserId(authenticatedUser.getUserId());
                    ticket.setEventId(event.getEventId());
                    ticket.setZoneName(selectedZone);
                    ticket.setPrice(selectedPrice.getPrice());
                    ticket.setQuantity(ticketsToBuy);

                    TicketsTemplate.addTicket(authenticatedUser.getUsername(), authenticatedUser.getPassword(), ticket);
                    System.out.println("La operación se ha completado satisfactoriamente");
                    //} catch (NoTicketsAvailableException e) {
                    //System.err.println("Operación cancelada: No hay tickets disponibles");
                    //} catch (TicketTransactionException e) {
                    //System.err.println("Operación cancelada: Error procesando transacción");
                    //}
                } else {
                    System.err.println("Operación cancelada");
                }
            } else {
                System.err.println("Todavía no se han asignado los precios para este evento");
            }
        } else {
            System.err.println("Operación cancelada");
        }
    }

    private void listTickets() {

        List<TicketDTO> tickets = UsersTemplate.getUserTickets(authenticatedUser.getUsername(), authenticatedUser.getPassword(), authenticatedUser.getUsername());

        if (!tickets.isEmpty()) {
            System.out.println();
            System.out.println("Tickets adquiridos por el usuario " + authenticatedUser.getUsername() + ": ");
            System.out.println("-----------------------------------");
            for (TicketDTO ticket : tickets) {

                EventDTO event = EventsTemplate.getEvent(authenticatedUser.getUsername(), authenticatedUser.getPassword(), ticket.getEventId());
                VenueDTO venue = VenuesTemplate.getVenue(authenticatedUser.getUsername(), authenticatedUser.getPassword(), event.getVenueId());

                System.out.println("Evento: " + event.getName());
                System.out.println("    Fecha: " + event.getDate());
                System.out.println("    Recinto: " + venue.getName());
                System.out.println("    Zona: " + ticket.getZoneName());
                System.out.println("    Precio por ticket: " + ticket.getPrice());
                System.out.println("    Cantidad comprada: " + ticket.getQuantity());
            }
        } else {
            System.out.println("Usted no hay comprado ningún ticket");
        }
    }

    private int printAdminMenu() {

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido " + authenticatedUser.getUsername() + ". Elija una opción:");
        System.out.println("1.- Añadir nuevo evento");
        System.out.println("0.- Logout");
        System.out.println("-----------------------------------");
        return readNumber();
    }

    private void parseAdminMenuOption(int op) {

        switch (op) {
            case 1:
                addNewEvent();
                break;
            case 0:
                logout();
                break;
            default:
                System.err.println("Opción inválida");
        }
    }

    private void addNewEvent() {

        EventDTO eventDTO = new EventDTO();

        System.out.println("Ingrese el nombre del evento:");
        eventDTO.setName(readText());

        System.out.println("Ingrese el tipo de evento:");
        eventDTO.setType(readText());

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        eventDTO.setDate(readDate());

        List<VenueDTO> venues = VenuesTemplate.getAllVenues(authenticatedUser.getUsername(), authenticatedUser.getPassword());

        Integer venueNumber = 0;
        for (VenueDTO venue : venues) {
            System.out.println("Recinto " + ++venueNumber + ": \"" + venue.getName() + "\"");
            System.out.println("  Localidad: " + venue.getCity());
            System.out.println("  Dirección: " + venue.getAddress());
        }

        System.out.println("Ingrese el número de recinto en el cual se celebra el evento ");
        do {
            venueNumber = readNumber();
        } while (venueNumber < 1 || venueNumber > venues.size());

        VenueDTO venue = venues.get(venueNumber - 1);

        eventDTO.setVenueId(venue.getVenueId());

        Collection<ZoneDTO> zones = venue.getZones();

        if (!zones.isEmpty()) {

            System.out.println("Zonas del recinto: ");
            for (ZoneDTO zone : zones) {
                System.out.println("  '" + zone.getName() + "' - " + zone.getSeats() + " pax.");
            }

            System.out.println("Ingrese los precios asignados para las zonas: ");
            for (ZoneDTO zone : zones) {
                System.out.println("  Precio para la zona '" + zone.getName() + "': ");
                BigDecimal price = readDecimalNumber();
                PricePerZoneDTO pricePerZone = new PricePerZoneDTO();
                pricePerZone.setZoneName(zone.getName());
                pricePerZone.setPrice(price);
                eventDTO.addPricePerZone(pricePerZone);
            }
        } else {
            System.out.println("El recinto no tiene zonas");
        }

        EventsTemplate.addEvent(authenticatedUser.getUsername(), authenticatedUser.getPassword(), eventDTO);
        System.out.println("El evento '" + eventDTO.getName() + "' ha sido creado correctamente");
    }

    private void registerUser() {

        System.out.println("Introduzca el nombre de usuario");
        String userName = readText();

        UserDTO userDTO = UsersTemplate.getUser("", "", userName);

        if (userDTO != null) {
            System.out.println("Introduzca la contraseña");
            String password = readText();
            userDTO.setPassword(password);
            UsersTemplate.addUser("", "", userDTO);
            System.out.println("El usuario " + userName + " ha sido registrado");
        } else {
            System.out.println("Nombre de usuario no disponible");
        }
    }

    private void authenticateUser() {

        System.out.println("Introduzca el nombre de usuario");
        String username = readText();
        System.out.println("Introduzca la contraseña");
        String password = readText();

        try {
            authenticatedUser = UsersTemplate.getUser(username, password, username);
            authenticatedUser.setPassword(password);
            System.out.println("Autenticación correcta");
        } catch (RuntimeException e) {
            System.err.println("El usuario o password son incorrectos");
            authenticatedUser = null;
        }
    }

    private void logout() {

        authenticatedUser = null;
        System.out.println("Se ha deslogueado correctamente");
    }

    private Integer readNumber() {

        do {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error leyendo número ingresado");
            }
        } while (true);
    }

    private BigDecimal readDecimalNumber() {

        do {
            try {
                return new BigDecimal(br.readLine().replace(",", "."));
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error leyendo número ingresado");
            }
        } while (true);
    }

    private String readText() {

        do {
            try {
                return br.readLine();
            } catch (IOException e) {
                System.err.println("Error leyendo texto ingresado");
            }
        } while (true);
    }

    private Character readCharacter() {

        do {
            try {
                return br.readLine().toUpperCase().charAt(0);
            } catch (IOException | StringIndexOutOfBoundsException e) {
                System.err.println("Error leyendo texto ingresado");
            }
        } while (true);
    }

    private String readDate() {

        do {
            try {
                String date = br.readLine();
                return LocalDate.parse(date, DATE_FORMAT).format(DATE_FORMAT);
            } catch (DateTimeParseException | IOException e) {
                System.err.println("Error en el formato de ingreso de la fecha");
            }
        } while (true);
    }
}
