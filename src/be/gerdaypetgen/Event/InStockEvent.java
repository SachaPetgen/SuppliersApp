/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen.Event;

import be.gerdaypetgen.Order;
import java.time.LocalDateTime;

/**
 *
 * @author sacha
 */
public class InStockEvent {
    
    public Order order;
    public boolean availibility;
    public LocalDateTime expeditionDate;

    public InStockEvent(Order order, boolean availibility, LocalDateTime expeditionDate) {
        this.order = order;
        this.availibility = availibility;
        this.expeditionDate = expeditionDate;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isAvailibility() {
        return availibility;
    }

    public LocalDateTime getExpeditionDate() {
        return expeditionDate;
    }
}
