/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen;

import be.gerdaypetgen.UI.SuppliersFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.Configuration;
import java.util.Properties;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import network.NetworkBasicServer;

/**
 *
 * @author sacha
 */
public class Main {
    
    public static SuppliersFrame suppliersFrame;
    
    public static NetworkBasicServer server;
    
    public static String name;
    
    private static int port;
    
    
        
    public static void main(String args[]){
                
        try{
             UIManager.setLookAndFeel(new NimbusLookAndFeel());

        }
        catch(UnsupportedLookAndFeelException e){
            System.out.printf("Look and feel Nimbus not found !\n");
        }
                
               
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(in);       
            
            port = Integer.parseInt(prop.getProperty("server.port"));
            
            name = prop.getProperty("name");
            
            
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        suppliersUI();
        server = new NetworkBasicServer(port, suppliersFrame.incomingMessageJCheckBox);
    }
    
    public static void suppliersUI(){
        
        suppliersFrame = new SuppliersFrame();
        Dimension size = new Dimension(900,600);
        suppliersFrame.setSize(size);
        suppliersFrame.setBackground(Color.GRAY);
        suppliersFrame.setMinimumSize(size);
        suppliersFrame.setTitle(name + " suppliers");
        suppliersFrame.setLocationRelativeTo(null);
        suppliersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        suppliersFrame.setVisible(true);
   
    }
        
}
