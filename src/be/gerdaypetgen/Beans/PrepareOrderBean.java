/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package be.gerdaypetgen.Beans;

import be.gerdaypetgen.Event.InStockEvent;
import be.gerdaypetgen.Event.InStockEventListener;
import be.gerdaypetgen.Event.SearchFoundEvent;
import be.gerdaypetgen.Event.SearchFoundEventListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sacha
 */
public class PrepareOrderBean implements Serializable, SearchFoundEventListener {
    
    private ArrayList<InStockEventListener> listeners;
    
    public PrepareOrderBean() {
        
        listeners = new ArrayList<>();
        
    }
    
    public void addEventListener(InStockEventListener listener){
        listeners.add(listener);
        
    }
    
    public void removeEventListener(InStockEventListener listener){
        listeners.remove(listener);
    }

    @Override
    public void searchFoundPerfomed(SearchFoundEvent e) {
        
        LocalDateTime now = LocalDateTime.now();
        Random rand = new Random();
        
        int random_days = rand.nextInt(14); 
        now.plusDays(random_days);

        
        InStockEvent inStockEvent = new InStockEvent(e.getOrder(), e.isAvailibility(), now);
        
        for(InStockEventListener li : listeners){
            li.inStockPerformed(inStockEvent);
        }
        
    }
}
