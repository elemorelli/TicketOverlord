package com.uja.dae.ticketlord.models;

import java.util.List;

public class Usuario {
    private Long token;
    private String nombre;
    private String password;
    private List<Ticket> tickets;
}
