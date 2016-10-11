package es.ujaen.dae.ticketoverlord.client;

import es.ujaen.dae.ticketoverlord.dtos.EventoDTO;
import es.ujaen.dae.ticketoverlord.dtos.PrecioPorZonaDTO;
import es.ujaen.dae.ticketoverlord.dtos.UsuarioDTO;
import es.ujaen.dae.ticketoverlord.services.VentaTicketsService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsoleClient {
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(isr);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static AbstractApplicationContext appContext = null;

    public static void main(String[] args) {

        appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        appContext.registerShutdownHook();

        int op;

        do {
            op = imprimirMenuPrincipal();
            op = parsearOpcion(op);
        } while (op != 0);
    }

    private static int imprimirMenuPrincipal() {
        int op;
        do {
            System.out.println();
            System.out.println("Elija una opción:");
            System.out.println("1.- Registrarse");
            System.out.println("2.- Login");
            System.out.println("0.- Salir");
            op = readInt();
        } while (op < 0 || op > 2);
        return op;
    }

    private static int parsearOpcion(int op) {
        switch (op) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                UsuarioDTO usuarioLogueado = autenticar();
                if (usuarioLogueado != null) do {
                    if (usuarioLogueado.isAdmin()) {
                        op = imprimirMenuAdmin(op, usuarioLogueado);
                        parsearOpcionAdmin(op);
                    } else {
                        op = imprimirMenuUsuario(op, usuarioLogueado);
                        parsearOpcionUsuario(op);
                    }
                } while (op != 0);
                op = -1;
                break;

            default:
                break;
        }
        return op;
    }

    private static int imprimirMenuUsuario(int op, UsuarioDTO usuarioLogueado) {
        do {
            if (usuarioLogueado != null) {
                System.out.println("Bienvenido " + usuarioLogueado.getNombre() + ". Elija una opción:");
                System.out.println("1.- Buscar eventos por nombre");
                System.out.println("2.- Buscar eventos por nombre y localidad");
                System.out.println("3.- Buscar eventos por fecha y tipo de evento");
                System.out.println("4.- Buscar eventos por fecha, tipo de evento y localidad");
                System.out.println("5.- Adquirir tickets");
                System.out.println("6.- Consultar Tickets adquiridos");
                System.out.println("0.- Logout");
                op = readInt();
            }
        } while (op < 0 || op > 4);
        return op;
    }

    private static void parsearOpcionUsuario(int op) {
        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");

        switch (op) {
            case 1:
                System.out.println("1.- Buscar eventos por nombre");
                System.out.println("Ingrese el nombre del evento:");
                String nombreEvento = readString();
                // TODO: Deberia ser una lista? Puede no ser unico
                // List<EventoDTO> eventos = ventaTicketsService.buscarEventoPorNombre(nombreEvento);
                EventoDTO evento = ventaTicketsService.buscarEventoPorNombre(nombreEvento);
                if (evento != null) {
                    System.out.println("Evento \"" + evento.getNombre() + "\"");
                    System.out.println("Tipo: " + evento.getTipo());
                    System.out.println("Fecha: " + dateFormat.format(evento.getFecha().getTime()));
                    System.out.println("Recinto: " + evento.getRecinto().getNombre());
                    System.out.println("Localidad: " + evento.getRecinto().getLocalidad());

                    List<PrecioPorZonaDTO> precios = evento.getPreciosPorZona();
                    if (!precios.isEmpty()) {
                        System.out.println("Precios:");
                        for (PrecioPorZonaDTO precioPorZona : precios) {
                            System.out.println("  Zona '" + precioPorZona.getZona().getNombre()
                                    + "' - $" + precioPorZona.getPrecio());
                        }
                    } else {
                        System.out.println("Todavía no se ha asignado los precios");
                    }
                } else {
                    System.out.println("No se ha encontrado el evento");
                }

                break;
            case 2:
                System.out.println("2.- Buscar eventos por nombre y localidad");
                break;
            case 3:
                System.out.println("3.- Buscar eventos por fecha y tipo de evento");
                break;
            case 4:
                System.out.println("4.- Buscar eventos por fecha, tipo de evento y localidad");
                break;
            case 5:
                System.out.println("5.- Adquirir tickets");
                break;
            case 6:
                System.out.println("6.- Consultar Tickets adquiridos");
                break;
        }
    }

    private static int imprimirMenuAdmin(int op, UsuarioDTO usuarioLogueado) {
        do {
            if (usuarioLogueado != null) {
                System.out.println("Bienvenido " + usuarioLogueado.getNombre() + ". Elija una opción:");
                System.out.println("1.- Buscar eventos por nombre");
                System.out.println("0.- Logout");
                op = readInt();
            }
        } while (op < 0 || op > 4);
        return op;
    }

    private static void parsearOpcionAdmin(int op) {
        switch (op) {
            case 1:
                System.out.println("1.- Buscar eventos por nombre");
                break;
        }
    }

    private static void registrarUsuario() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");

        System.out.println("Introduzca el nombre de usuario");
        String nombreUsuario = readString();

        System.out.println("Introduzca la contraseña");
        String password = readString();

        UsuarioDTO usuarioDTO = new UsuarioDTO(nombreUsuario, password);

        if (!ventaTicketsService.existeUsuario(usuarioDTO)) {
            ventaTicketsService.agregarUsuario(usuarioDTO);
            System.out.println("El usuario " + nombreUsuario + " ha sido registrado");
        } else {
            System.out.println("Nombre de usuario no disponible");
        }
    }

    private static UsuarioDTO autenticar() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");

        System.out.println("Introduzca el nombre de usuario");
        String nombreUsuario = readString();

        System.out.println("Introduzca la contraseña");
        String password = readString();

        UsuarioDTO usuarioAAutenticar = new UsuarioDTO(nombreUsuario, password);

        if (ventaTicketsService.existeUsuario(usuarioAAutenticar)) {

            UsuarioDTO usuarioAValidar = ventaTicketsService.getDatosUsuario(usuarioAAutenticar);
            if (usuarioAValidar.getPassword().equals(usuarioAAutenticar.getPassword())) {
                System.out.println("Autenticación correcta");
                return usuarioAValidar;
            } else {
                System.err.println("Password incorrecto");
                return null;
            }
        } else {
            System.err.println("El usuario no existe");
            return null;
        }
    }

    private static Integer readInt() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error leyendo comando");
        }
        return -1;
    }

    private static String readString() {
        try {
            return br.readLine();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error leyendo comando");
        }
        return "";
    }
}
