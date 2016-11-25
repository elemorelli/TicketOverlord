package es.ujaen.dae.ticketoverlord.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
@ComponentScan("es.ujaen.dae.ticketoverlord")
public class AppConfiguration {
}
