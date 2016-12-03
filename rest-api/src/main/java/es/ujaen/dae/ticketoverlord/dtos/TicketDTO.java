package es.ujaen.dae.ticketoverlord.dtos;

import es.ujaen.dae.ticketoverlord.models.Ticket;
import es.ujaen.dae.ticketoverlord.resources.v1.EventsResource;
import es.ujaen.dae.ticketoverlord.resources.v1.TicketsResource;
import es.ujaen.dae.ticketoverlord.resources.v1.UsersResource;
import es.ujaen.dae.ticketoverlord.resources.v1.VenuesResource;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class TicketDTO extends ResourceSupport {
    private Integer ticketId;
    private Integer userId;
    private Integer eventId;
    private Character zoneName;
    private BigDecimal price;
    private Integer quantity;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.ticketId = ticket.getId();
        this.userId = ticket.getUser().getId();
        this.eventId = ticket.getEvent().getEventId();
        this.zoneName = ticket.getZone().getName();
        this.price = ticket.getPrice();
        this.quantity = ticket.getQuantity();

        this.add(linkTo(TicketsResource.class)
                .slash(this.getTicketId()).withSelfRel());

        this.add(linkTo(EventsResource.class)
                .slash(this.getEventId()).withRel("event"));

        this.add(linkTo(UsersResource.class)
                .slash(this.getUserId()).withRel("user"));

        this.add(linkTo(VenuesResource.class)
                .slash(ticket.getEvent().getVenue().getId())
                .slash("zones")
                .slash(this.getZoneName()).withRel("zone"));
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketId=" + ticketId +
                ", event=" + eventId +
                ", zone=" + zoneName +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Character getZoneName() {
        return zoneName;
    }

    public void setZoneName(Character zoneName) {
        this.zoneName = zoneName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
