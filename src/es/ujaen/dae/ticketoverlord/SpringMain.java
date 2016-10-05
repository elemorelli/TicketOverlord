package es.ujaen.dae.ticketoverlord;

import es.ujaen.dae.ticketoverlord.dtos.UsuarioDTO;
import es.ujaen.dae.ticketoverlord.services.VentaTicketsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {

        ApplicationContext appContext = new
                ClassPathXmlApplicationContext("resources/applicationContext.xml");

        VentaTicketsService ventaTicketsService = (VentaTicketsService) appContext.getBean("ventaTickets");

        System.out.println("-- Usuarios:");
        System.out.println(ventaTicketsService.listarUsuarios());

        ventaTicketsService.agregarUsuario(new UsuarioDTO("eleazar", "password"));

        System.out.println("-- Usuarios:");
        System.out.println(ventaTicketsService.listarUsuarios());

        System.out.println("-- Recintos:");
        System.out.println(ventaTicketsService.listarRecintos());
    }
}
