/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package be.gerdaypetgen.Beans;

import be.gerdaypetgen.Event.InStockEvent;
import be.gerdaypetgen.Event.InStockEventListener;
import be.gerdaypetgen.Main;
import java.beans.*;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sacha
 */
public class ReceivingBean implements Serializable, InStockEventListener {
        
    private String commandeString;
    
    private PropertyChangeSupport propertySupport;
    
    public ReceivingBean() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public void read(){
        System.out.print("methode read");
        String message = Main.server.getMessage();
        setCommande(message);
    }
    
    public String getCommande() {
        return commandeString;
    }
    
    public void setCommande(String value) {
        String oldValue = commandeString;
        commandeString = value;
        propertySupport.firePropertyChange("commandeString", oldValue, commandeString);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    @Override
    public void inStockPerformed(InStockEvent e) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 

        String response = e.order.toSend() + ";" + (e.availibility ? "1" : "0") + ";" + dtf.format(e.expeditionDate);

        Main.server.sendMessage(response);
        
    }
    
}
