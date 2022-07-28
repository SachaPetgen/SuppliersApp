/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package be.gerdaypetgen.Beans;

import be.gerdaypetgen.Event.SearchFoundEvent;
import be.gerdaypetgen.Event.SearchFoundEventListener;
import be.gerdaypetgen.Main;
import be.gerdaypetgen.Order;
import be.gerdaypetgen.OrderPriorityEnum;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author sacha
 */
public class SearchBean implements Serializable, PropertyChangeListener{
    
    public final Vector<Order> pendingOrdersList = new Vector<>();
    
    private ArrayList<SearchFoundEventListener> listeners;
    
    public SearchBean() {
        listeners = new ArrayList<>();
    }
    
    public void addEventListener(SearchFoundEventListener listener){
        listeners.add(listener);
    }
    
    public void removeEventListener(SearchFoundEventListener listener){
        listeners.remove(listener);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
               
        System.out.print("Property Changed");

        String [] rowSplit = ((String) evt.getNewValue()).split(";");
                        
        if(rowSplit.length == 4){

            OrderPriorityEnum priority = OrderPriorityEnum.NOT_PRIORITIZE;

            for(OrderPriorityEnum priorityEnum : OrderPriorityEnum.values()){
                if(priorityEnum.getValue() == Integer.parseInt(rowSplit[3])){
                    priority = priorityEnum;
                }
            }

            Order order = new Order(rowSplit[0], rowSplit[1], Integer.parseInt(rowSplit[2]), priority);

            pendingOrdersList.add(order);
            Main.suppliersFrame.orderStringJLabel.setText(order.toString());
            Main.suppliersFrame.pendingOrdersJComboBox.setSelectedIndex(Main.suppliersFrame.pendingOrdersJComboBox.getItemCount() - 1);

        }
    }

    
    public void response(boolean available){
        Order order = pendingOrdersList.get(Main.suppliersFrame.pendingOrdersJComboBox.getSelectedIndex());
        SearchFoundEvent searchFoundEvent = new SearchFoundEvent(order, available);
        pendingOrdersList.remove(order);
        for(SearchFoundEventListener li : listeners){
            li.searchFoundPerfomed(searchFoundEvent);
        }
    }
}

