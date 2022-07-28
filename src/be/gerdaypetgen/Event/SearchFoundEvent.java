/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen.Event;

import be.gerdaypetgen.Order;

/**
 *
 * @author sacha
 */
public class SearchFoundEvent {
    
    private Order order;
    private boolean availibility;

    public SearchFoundEvent(Order order, boolean availibility) {
        this.order = order;
        this.availibility = availibility;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isAvailibility() {
        return availibility;
    }
}
