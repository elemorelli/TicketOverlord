package es.ujaen.dae.ticketoverlord.client;

import es.ujaen.dae.ticketoverlord.dtos.EventDTO;
import es.ujaen.dae.ticketoverlord.dtos.PricePerZoneDTO;
import es.ujaen.dae.ticketoverlord.dtos.UserDTO;
import es.ujaen.dae.ticketoverlord.services.EventsService;
import es.ujaen.dae.ticketoverlord.services.TicketsService;
import es.ujaen.dae.ticketoverlord.services.UsersService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleClient {
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(isr);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static AbstractApplicationContext appContext = null;
    private static UserDTO authenticatedUser = null;

    public static UserDTO getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static AbstractApplicationContext getAppContext() {
        return appContext;
    }

    public static void main(String[] args) {

        appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        appContext.registerShutdownHook();

        int op;
        do {
            op = printMainMenu();
            parseMainMenuOption(op);
        } while (op != 0);
    }

    private static int printMainMenu() {
        System.out.println();
        System.out.println("Elija una opción:");
        System.out.println("1.- Registrarse");
        System.out.println("2.- Login");
        System.out.println("0.- Salir");
        return readNumber();
    }

    private static void parseMainMenuOption(int op) {

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

    private static int printUserMenu() {

        System.out.println("Bienvenido " + authenticatedUser.getName() + ". Elija una opción:");
        System.out.println("1.- Buscar eventos por nombre");
        System.out.println("2.- Buscar eventos por nombre y localidad");
        System.out.println("3.- Buscar eventos por fecha y tipo de evento");
        System.out.println("4.- Buscar eventos por fecha, tipo de evento y localidad");
        System.out.println("5.- Adquirir tickets");
        System.out.println("6.- Consultar Tickets adquiridos");
        System.out.println("0.- Logout");
        return readNumber();
    }

    private static void printUserMenuOption(int op) {

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
                buyTickets();
                break;
            case 6:
                listTickets();
                break;
            case 0:
                logout();
                break;
            default:
                System.err.println("Opción inválida");
        }
    }

    private static void findEventsByName() {

        EventsService eventsService = (EventsService) appContext.getBean("eventsService");
        System.out.println("Ingrese el nombre del evento:");
        String eventName = readText();
        List<EventDTO> events = eventsService.findEventsByName(eventName);
        if (!events.isEmpty()) {
            printEventList(events);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre");
        }
    }

    private static void findEventsByNameAndCity() {

        EventsService eventsService = (EventsService) appContext.getBean("eventsService");
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

    private static void findEventsByDateAndType() {

        EventsService eventsService = (EventsService) appContext.getBean("eventsService");
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

    private static void findEventsByDateTypeAndCity() {

        EventsService eventsService = (EventsService) appContext.getBean("eventsService");
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

    private static void printEventList(List<EventDTO> events) {

        System.out.println("Event encontrados:");
        for (EventDTO event : events) {
            System.out.println(" \"" + event.getName() + "\"");
            System.out.println("    Tipo: " + event.getType());
            System.out.println("    Fecha: " + event.getDate().format(dateFormatter));
            System.out.println("    Venue: " + event.getVenue().getName());
            System.out.println("    Localidad: " + event.getVenue().getCity());

            List<PricePerZoneDTO> pricesPerZone = event.getPricesPerZone();
            if (!pricesPerZone.isEmpty()) {
                System.out.println("    Precios:");
                for (PricePerZoneDTO pricePerZoneDTO : pricesPerZone) {
                    System.out.println("      Zone '" + pricePerZoneDTO.getZone().getName()
                            + "' - $" + pricePerZoneDTO.getPrice());
                }
            } else {
                System.out.println("    Todavía no se ha asignado los precios");
            }
        }
    }

    private static void buyTickets() {
        // TODO
        TicketsService ticketsService = (TicketsService) appContext.getBean("ticketsService");
        ticketsService.buyTicket(null, null);
    }

    private static void listTickets() {
        // TODO
        TicketsService ticketsService = (TicketsService) appContext.getBean("ticketsService");
        ticketsService.listTickets(authenticatedUser);
    }

    private static int printAdminMenu() {

        System.out.println("Bienvenido " + authenticatedUser.getName() + ". Elija una opción:");
        System.out.println("1.- Añadir nuevo evento");
        System.out.println("0.- Logout");
        return readNumber();
    }

    private static void parseAdminMenuOption(int op) {

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

    private static void addNewEvent() {
        // TODO
        EventsService eventsService = (EventsService) appContext.getBean("eventsService");
        eventsService.addNewEvent(null);
    }

    private static void registerUser() {

        UsersService usersService = (UsersService) appContext.getBean("usersService");
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

    private static void authenticateUser() {

        UsersService usersService = (UsersService) appContext.getBean("usersService");
        UserDTO user = new UserDTO();
        System.out.println("Introduzca el nombre de usuario");
        String userName = readText();
        user.setName(userName);

        if (usersService.userExists(user)) {
            System.out.println("Introduzca la contraseña");
            String password = readText();
            user.setPassword(password);

            if (usersService.authenticateUser(user)) {
                System.out.println("Autenticación correcta");
                authenticatedUser = usersService.getUserData(user);
            } else {
                System.err.println("Password incorrecto");
                authenticatedUser = null;
            }
        } else {
            System.err.println("El usuario no existe");
            authenticatedUser = null;
        }
    }

    private static void logout() {

        UsersService usersService = (UsersService) appContext.getBean("usersService");
        usersService.logoutUser(authenticatedUser);
        authenticatedUser = null;
        System.out.println("Se ha deslogueado correctamente");
    }

    private static Integer readNumber() {

        do {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error leyendo número ingresado");
            }
        } while (true);
    }

    private static String readText() {

        do {
            try {
                return br.readLine();
            } catch (IOException e) {
                System.err.println("Error leyendo texto ingresado");
            }
        } while (true);
    }

    private static LocalDate readDate() {

        do {
            try {
                String date = br.readLine();
                return LocalDate.parse(date, dateFormatter);
            } catch (IOException e) {
                System.err.println("Error en el formato de ingreso de la fecha");
            }
        } while (true);
    }
}
