package es.ujaen.dae.ticketoverlord.client;

import es.ujaen.dae.ticketoverlord.configurations.AppConfiguration;
import es.ujaen.dae.ticketoverlord.dtos.*;
import es.ujaen.dae.ticketoverlord.exceptions.NoTicketsAvailableException;
import es.ujaen.dae.ticketoverlord.exceptions.TicketTransactionException;
import es.ujaen.dae.ticketoverlord.services.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static es.ujaen.dae.ticketoverlord.AppInitializer.DATE_FORMAT;

public class ConsoleClient {
    private final InputStreamReader isr = new InputStreamReader(System.in);
    private final BufferedReader br = new BufferedReader(isr);
    private final List<String> affirmatives = Arrays.asList("S", "SI", "SÍ", "Y", "YES");
    private final List<String> negatives = Arrays.asList("N", "NO");
    private UserDTO authenticatedUser = null;
    private EventsService eventsService;
    private TicketsService ticketsService;
    private UsersService usersService;
    private SessionService sessionService;
    private VenuesService venuesService;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        appContext.registerShutdownHook();

        ConsoleClient client = new ConsoleClient();
        client.eventsService = (EventsService) appContext.getBean("EventsService");
        client.ticketsService = (TicketsService) appContext.getBean("TicketsService");
        client.usersService = (UsersService) appContext.getBean("UsersService");
        client.sessionService = (SessionService) appContext.getBean("SessionService");
        client.venuesService = (VenuesService) appContext.getBean("VenuesService");

        client.mainLoop();
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
        System.out.println("Bienvenido " + authenticatedUser.getName() + ". Elija una opción:");
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
        List<EventDTO> events = eventsService.findEventsByName(eventName);
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
        List<EventDTO> events = eventsService.findEventsByNameAndCity(eventName, eventCity);
        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre en esa localidad");
        }
    }

    private void findEventsByDateAndType() {

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        LocalDate eventDate = readDate();
        System.out.println("Ingrese el tipo de evento:");
        String eventType = readText();
        List<EventDTO> events = eventsService.findEventsByDateAndType(eventDate, eventType);
        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos de ese tipo en esa fecha");
        }
    }

    private void findEventsByDateTypeAndCity() {

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        LocalDate eventDate = readDate();
        System.out.println("Ingrese el tipo de evento:");
        String eventType = readText();
        System.out.println("Ingrese la localidad");
        String eventCity = readText();
        List<EventDTO> events = eventsService.findEventsByDateTypeAndCity(eventDate, eventType, eventCity);
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
            System.out.println();
            System.out.println("  EVENTO " + ++eventNumber + ": \"" + event.getName() + "\"");
            System.out.println("    Tipo: " + event.getType());
            System.out.println("    Fecha: " + event.getDate());
            System.out.println("    Recinto: " + event.getVenue().getName());
            System.out.println("    Localidad: " + event.getVenue().getCity());

            Map<Character, PricePerZoneDTO> pricesPerZone = event.getPricesPerZone();
            if (!pricesPerZone.isEmpty()) {
                System.out.println("    Precios:");

                for (PricePerZoneDTO pricePerZoneDTO : pricesPerZone.values()) {
                    System.out.print("      Zone '" + pricePerZoneDTO.getZone().getName()
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

            Map<Character, PricePerZoneDTO> prices = event.getPricesPerZone();
            if (prices != null && !prices.isEmpty()) {
                System.out.println("Seleccione zona a la cual desea asistir:");

                for (PricePerZoneDTO pricePerZoneDTO : prices.values()) {
                    System.out.print("Zona '" + pricePerZoneDTO.getZone().getName()
                            + "' a €" + pricePerZoneDTO.getPrice());
                    if (pricePerZoneDTO.getAvailableSeats() > 0) {
                        System.out.println(" (" + pricePerZoneDTO.getAvailableSeats() + " tickets disponibles)");
                    } else {
                        System.out.println(" ENTRADAS AGOTADAS");
                    }
                }

                Character selectedZone;
                do {
                    selectedZone = readCharacter();
                    if (!prices.containsKey(selectedZone)) {
                        System.err.println("La zona ingresada no existe");
                    } else if (prices.get(selectedZone).getAvailableSeats() <= 0) {
                        System.err.println("TICKETS AGOTADOS PARA LA ZONA '" + selectedZone + "'");
                    }
                }
                while (!prices.containsKey(selectedZone) || prices.get(selectedZone).getAvailableSeats() <= 0);

                PricePerZoneDTO priceToCharge = prices.get(selectedZone);

                Integer ticketsToBuy;
                do {
                    System.out.println("Ingrese la cantidad de tickets (" + priceToCharge.getAvailableSeats() + " tickets disponibles):");
                    ticketsToBuy = readNumber();
                } while (ticketsToBuy <= 0 || ticketsToBuy > priceToCharge.getAvailableSeats());

                System.out.println();
                System.out.println("-----------------------------------");
                System.out.println("Resumen de la compra:");
                System.out.println("  Evento: " + event.getName());
                System.out.println("  Fecha: " + event.getDate());
                System.out.println("  Recinto: " + event.getVenue().getName());
                System.out.println("  Zona: " + priceToCharge.getZone().getName());
                System.out.println("  Precio por ticket: €" + priceToCharge.getPrice());
                System.out.println("  Cantidad de tickets: " + ticketsToBuy);
                System.out.println("  Se le facturará un total de €" + (priceToCharge.getPrice().multiply(new BigDecimal(ticketsToBuy))));
                System.out.println("-----------------------------------");
                System.out.println();
                System.out.println("  ¿Desea confirmar la operación? S/N");

                do {
                    input = readText().toUpperCase();
                } while (!affirmatives.contains(input) && !negatives.contains(input));

                if (affirmatives.contains(input)) {
                    try {
                        ticketsService.buyTicket(authenticatedUser, event, priceToCharge, ticketsToBuy);
                        System.out.println("La operación se ha completado satisfactoriamente");
                    } catch (NoTicketsAvailableException e) {
                        System.err.println("Operación cancelada: No hay tickets disponibles");
                    } catch (TicketTransactionException e) {
                        System.err.println("Operación cancelada: Error procesando transacción");
                    }
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
        List<TicketDTO> tickets = ticketsService.listTicketsByUser(authenticatedUser);

        if (!tickets.isEmpty()) {
            System.out.println();
            System.out.println("Tickets adquiridos por el usuario " + authenticatedUser.getName() + ": ");
            System.out.println("-----------------------------------");
            for (TicketDTO ticket : tickets) {
                System.out.println("Evento: " + ticket.getEvent().getName());
                System.out.println("    Fecha: " + ticket.getEvent().getDate());
                System.out.println("    Recinto: " + ticket.getEvent().getVenue().getName());
                System.out.println("    Zona: " + ticket.getZone().getName());
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
        System.out.println("Bienvenido " + authenticatedUser.getName() + ". Elija una opción:");
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

        EventDTO eventdto = new EventDTO();

        System.out.println("Ingrese el nombre del evento:");
        eventdto.setName(readText());

        System.out.println("Ingrese el tipo de evento:");
        eventdto.setType(readText()); // TODO: Posible Enum y mostrarlo como lista?

        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        eventdto.setDate(readDate().format(DATE_FORMAT));

        List<VenueDTO> venues = venuesService.getVenues();

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

        eventdto.setVenue(venue);

        Collection<ZoneDTO> zones = venue.getZones().values();

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
                pricePerZone.setZone(zone);
                pricePerZone.setPrice(price);
                eventdto.addPricesPerZone(pricePerZone);
            }
        } else {
            System.out.println("El recinto no tiene zonas");
        }

        eventsService.addNewEvent(authenticatedUser, eventdto);
        System.out.println("El evento '" + eventdto.getName() + "' ha sido creado correctamente");
    }

    private void registerUser() {

        UserDTO userDTO = new UserDTO();
        System.out.println("Introduzca el nombre de usuario");
        String userName = readText();
        userDTO.setName(userName);

        if (!usersService.userExists(userDTO)) {
            System.out.println("Introduzca la contraseña");
            String password = readText();
            userDTO.setPassword(password);
            usersService.addNewUser(userDTO);
            System.out.println("El usuario " + userName + " ha sido registrado");
        } else {
            System.out.println("Nombre de usuario no disponible");
        }
    }

    private void authenticateUser() {

        UserDTO user = new UserDTO();
        System.out.println("Introduzca el nombre de usuario");
        String userName = readText();
        user.setName(userName);

        if (usersService.userExists(user)) {
            System.out.println("Introduzca la contraseña");
            String password = readText();
            user.setPassword(password);

            try {
                sessionService.authenticateUser(user);
                System.out.println("Autenticación correcta");
                authenticatedUser = usersService.getUser(user);
            } catch (RuntimeException e) {
                System.err.println("Password incorrecto");
                authenticatedUser = null;
            }
        } else {
            System.err.println("El usuario no existe");
            authenticatedUser = null;
        }
    }

    private void logout() {

        sessionService.logoutUser(authenticatedUser);
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

    private LocalDate readDate() {

        do {
            try {
                String date = br.readLine();
                return LocalDate.parse(date, DATE_FORMAT);
            } catch (DateTimeParseException | IOException e) {
                System.err.println("Error en el formato de ingreso de la fecha");
            }
        } while (true);
    }
}
