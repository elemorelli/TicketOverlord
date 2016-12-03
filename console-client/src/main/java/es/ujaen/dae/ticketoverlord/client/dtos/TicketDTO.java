package es.ujaen.dae.ticketoverlord.client.dtos;

import java.math.BigDecimal;

public class TicketDTO {
    private Integer ticketId;
    private Integer userId;
    private Integer eventId;
    private Character zoneName;
    private BigDecimal price;
    private Integer quantity;

    public TicketDTO() {
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
