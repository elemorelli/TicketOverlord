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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleClient {
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(isr);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static AbstractApplicationContext appContext = null;
    private static UsuarioDTO usuarioLogueado = null;

    public static void main(String[] args) {

        appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        appContext.registerShutdownHook();

        int op;
        do {
            op = imprimirMenuPrincipal();
            parsearOpcion(op);
        } while (op != 0);
    }

    private static int imprimirMenuPrincipal() {
        System.out.println();
        System.out.println("Elija una opción:");
        System.out.println("1.- Registrarse");
        System.out.println("2.- Login");
        System.out.println("0.- Salir");
        return ingresarNumero();
    }

    private static void parsearOpcion(int op) {
        switch (op) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                autenticar();
                if (usuarioLogueado != null) do {
                    if (usuarioLogueado.isAdmin()) {
                        op = imprimirMenuAdmin();
                        parsearOpcionAdmin(op);
                    } else {
                        op = imprimirMenuUsuario();
                        parsearOpcionUsuario(op);
                    }
                } while (op != 0);
                break;
            case 0:
                System.out.println("Gracias por utilizar el sistema TicketLord. ¡Hasta pronto!");
                break;
            default:
                System.err.println("Opción inválida");
                break;
        }
    }

    private static int imprimirMenuUsuario() {
        System.out.println("Bienvenido " + usuarioLogueado.getNombre() + ". Elija una opción:");
        System.out.println("1.- Buscar eventos por nombre");
        System.out.println("2.- Buscar eventos por nombre y localidad");
        System.out.println("3.- Buscar eventos por fecha y tipo de evento");
        System.out.println("4.- Buscar eventos por fecha, tipo de evento y localidad");
        System.out.println("5.- Adquirir tickets");
        System.out.println("6.- Consultar Tickets adquiridos");
        System.out.println("0.- Logout");
        return ingresarNumero();
    }

    private static void parsearOpcionUsuario(int op) {

        switch (op) {
            case 1:
                buscarEventosPorNombre();
                break;
            case 2:
                buscarEventosPorNombreYLocalidad();
                break;
            case 3:
                buscarEventosPorFechaYTipo();
                break;
            case 4:
                buscarEventosPorFechaTipoYLocalidad();
                break;
            case 5:
                adquirirTickets();
                break;
            case 6:
                consultarTicketsAdquiridos();
                break;
            case 0:
                System.out.println("Se ha deslogueado correctamente");
                usuarioLogueado = null;
                break;
            default:
                System.err.println("Opción inválida");
        }
    }

    private static void buscarEventosPorNombre() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        System.out.println("Ingrese el nombre del evento:");
        String nombreEvento = ingresarTexto();
        List<EventoDTO> eventos = ventaTicketsService.buscarEventosPorNombre(nombreEvento);
        if (!eventos.isEmpty()) {
            imprimirListaDeEventos(eventos);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre");
        }
    }

    private static void buscarEventosPorNombreYLocalidad() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        System.out.println("Ingrese el nombre del evento:");
        String nombreEvento = ingresarTexto();
        System.out.println("Ingrese la localidad");
        String localidadEvento = ingresarTexto();
        List<EventoDTO> eventos = ventaTicketsService.buscarEventosPorNombreYLocalidad(nombreEvento, localidadEvento);
        if (!eventos.isEmpty()) {
            imprimirListaDeEventos(eventos);
        } else {
            System.out.println("No se han encontrado eventos con ese nombre en esa localidad");
        }
    }

    private static void buscarEventosPorFechaYTipo() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        LocalDate fechaEvento = ingresarFecha();
        System.out.println("Ingrese el tipo de evento:");
        String tipoEvento = ingresarTexto();
        List<EventoDTO> eventos = ventaTicketsService.buscarEventosPorFechaYTipo(fechaEvento, tipoEvento);
        if (!eventos.isEmpty()) {
            imprimirListaDeEventos(eventos);
        } else {
            System.out.println("No se han encontrado eventos de ese tipo en esa fecha");
        }
    }

    private static void buscarEventosPorFechaTipoYLocalidad() {
        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        System.out.println("Ingrese la fecha del evento (Formato dd/mm/aaaa):");
        LocalDate fechaEvento = ingresarFecha();
        System.out.println("Ingrese el tipo de evento:");
        String tipoEvento = ingresarTexto();
        System.out.println("Ingrese la localidad");
        String localidadEvento = ingresarTexto();
        List<EventoDTO> eventos = ventaTicketsService.buscarEventosPorFechaTipoYLocalidad(fechaEvento, tipoEvento,
                localidadEvento);
        if (!eventos.isEmpty()) {
            imprimirListaDeEventos(eventos);
        } else {
            System.out.println("No se han encontrado eventos de ese tipo en esa fecha y en esa localidad");
        }
    }

    private static void imprimirListaDeEventos(List<EventoDTO> eventos) {

        System.out.println("Evento encontrados:");
        for (EventoDTO evento : eventos) {
            System.out.println(" \"" + evento.getNombre() + "\"");
            System.out.println("    Tipo: " + evento.getTipo());
            System.out.println("    Fecha: " + evento.getFecha().format(dateFormatter));
            System.out.println("    Recinto: " + evento.getRecinto().getNombre());
            System.out.println("    Localidad: " + evento.getRecinto().getLocalidad());

            List<PrecioPorZonaDTO> precios = evento.getPreciosPorZona();
            if (!precios.isEmpty()) {
                System.out.println("    Precios:");
                for (PrecioPorZonaDTO precioPorZona : precios) {
                    System.out.println("      Zona '" + precioPorZona.getZona().getNombre()
                            + "' - $" + precioPorZona.getPrecio());
                }
            } else {
                System.out.println("    Todavía no se ha asignado los precios");
            }
        }
    }

    private static void adquirirTickets() {
        // TODO
    }

    private static void consultarTicketsAdquiridos() {
        // TODO
    }

    private static int imprimirMenuAdmin() {
        System.out.println("Bienvenido " + usuarioLogueado.getNombre() + ". Elija una opción:");
        System.out.println("1.- Añadir nuevo evento");
        System.out.println("0.- Logout");
        return ingresarNumero();
    }

    private static void parsearOpcionAdmin(int op) {
        switch (op) {
            case 1:
                anadirNuevoEvento();
                break;
            case 0:
                System.out.println("Se ha deslogueado correctamente");
                usuarioLogueado = null;
                break;
            default:
                System.err.println("Opción inválida");
        }
    }

    private static void anadirNuevoEvento() {
        // TODO
    }

    private static void registrarUsuario() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        System.out.println("Introduzca el nombre de usuario");
        String nombreUsuario = ingresarTexto();
        usuarioDTO.setNombre(nombreUsuario);

        if (!ventaTicketsService.existeUsuario(usuarioDTO)) {
            System.out.println("Introduzca la contraseña");
            String password = ingresarTexto();
            usuarioDTO.setPassword(password);
            ventaTicketsService.agregarUsuario(usuarioDTO);
            System.out.println("El usuario " + nombreUsuario + " ha sido registrado");
        } else {
            System.out.println("Nombre de usuario no disponible");
        }
    }

    private static void autenticar() {

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");
        UsuarioDTO usuario = new UsuarioDTO();
        System.out.println("Introduzca el nombre de usuario");
        String nombreUsuario = ingresarTexto();
        usuario.setNombre(nombreUsuario);

        if (ventaTicketsService.existeUsuario(usuario)) {
            System.out.println("Introduzca la contraseña");
            String password = ingresarTexto();
            usuario.setPassword(password);

            if (ventaTicketsService.autenticarUsuario(usuario)) {
                System.out.println("Autenticación correcta");
                usuarioLogueado = ventaTicketsService.getDatosUsuario(usuario);
            } else {
                System.err.println("Password incorrecto");
                usuarioLogueado = null;
            }
        } else {
            System.err.println("El usuario no existe");
            usuarioLogueado = null;
        }
    }

    private static Integer ingresarNumero() {

        Integer number;
        do {
            try {
                number = Integer.parseInt(br.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.err.println("Error leyendo número ingresado");
            }
        } while (true);
        return number;
    }

    private static String ingresarTexto() {

        String texto;
        do {
            try {
                texto = br.readLine();
                break;
            } catch (IOException e) {
                System.err.println("Error leyendo texto ingresado");
            }
        } while (true);
        return texto;
    }

    private static LocalDate ingresarFecha() {

        LocalDate fechaParseada;
        do {
            try {
                String fecha = br.readLine();
                fechaParseada = LocalDate.parse(fecha, dateFormatter);
                break;
            } catch (IOException e) {
                System.err.println("Error en el formato de ingreso de la fecha");
            }
        } while (true);
        return fechaParseada;
    }
}
