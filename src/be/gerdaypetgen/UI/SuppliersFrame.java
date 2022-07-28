/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen.UI;

import be.gerdaypetgen.Beans.PrepareOrderBean;
import be.gerdaypetgen.Beans.ReceivingBean;
import be.gerdaypetgen.Beans.SearchBean;
import be.gerdaypetgen.Main;
import be.gerdaypetgen.Order;
import be.gerdaypetgen.OrderPriorityEnum;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sacha
 */
public class SuppliersFrame extends JFrame implements ActionListener{

    public JLabel orderStringJLabel;
    public JCheckBox incomingMessageJCheckBox;
    public JComboBox pendingOrdersJComboBox;
    private DefaultTableModel model;
    private ButtonGroup bg;
    
    private ReceivingBean receivingBean;    
    private SearchBean searchBean;
    private PrepareOrderBean prepareOrderBean;
                       
            
    public SuppliersFrame(){
        
        receivingBean = new ReceivingBean();
        searchBean = new SearchBean();
        prepareOrderBean = new PrepareOrderBean();
        
        receivingBean.addPropertyChangeListener(searchBean);
        searchBean.addEventListener(prepareOrderBean);
        prepareOrderBean.addEventListener(receivingBean);
        
        initComponents();

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton jButton){

                switch (jButton.getName()) {
                    case "readButton" ->{
                        
                        receivingBean.read();

                        break;
                    }
                    case "setAvailibilityButton" -> {
                                               
                        boolean available = false;
                        
                        if(bg.getSelection().getActionCommand().equals("Available")){
                            available = true;
                        } 
                                                                        
                        searchBean.response(available);
                        
                    }
                    case "sendButton" -> {
                        
                                       
                    }
                }
        }
        else{
            if(e.getSource() instanceof JComboBox jComboBox){
                if(jComboBox.getName().equals("pendingOrdersCombo")){
                    
                    model.setRowCount(0);
                    Order order = searchBean.pendingOrdersList.get(jComboBox.getSelectedIndex());
                    
                    String [] rowSplit = order.toString().split(" ");
                    
                    model.addRow(rowSplit);
                    
                }
            }
        }
        
    }
   
    private void initComponents() {
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(5, 0, 50, 50));  
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        
        JPanel readPanel = new JPanel();
        readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.Y_AXIS));
        
        incomingMessageJCheckBox = new JCheckBox("Incoming message");
        incomingMessageJCheckBox.setAlignmentX(CENTER_ALIGNMENT);
        
        JButton readJButton = new JButton("Read");
        readJButton.addActionListener(this);
        readJButton.setName("readButton");
        readJButton.setAlignmentX(CENTER_ALIGNMENT);

        readPanel.add(incomingMessageJCheckBox);
        readPanel.add(Box.createVerticalGlue());
        readPanel.add(readJButton);
        
        
        JPanel pendingPanel = new JPanel();
        pendingPanel.setLayout(new BoxLayout(pendingPanel, BoxLayout.Y_AXIS));

        JLabel pendingOrders = new JLabel("Pending orders :");
        pendingOrders.setAlignmentX(CENTER_ALIGNMENT);

        
        pendingOrdersJComboBox = new JComboBox<>(searchBean.pendingOrdersList);
        pendingOrdersJComboBox.setName("pendingOrdersCombo");
        pendingOrdersJComboBox.addActionListener(this);
        pendingOrdersJComboBox.setAlignmentX(CENTER_ALIGNMENT);

        pendingPanel.add(pendingOrders);
        pendingPanel.add(Box.createVerticalGlue());
        pendingPanel.add(pendingOrdersJComboBox);
        
        
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBorder(new MetalBorders.TextFieldBorder());
        imagePanel.setBackground(Color.LIGHT_GRAY);
        
        JPanel borderImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        borderImagePanel.setBorder(new EmptyBorder(10,10,10,10));
        borderImagePanel.setBackground(Color.LIGHT_GRAY);

        JLabel supplierTypeJLabel = new JLabel(Main.name, JLabel.CENTER);
        
        supplierTypeJLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
 
        supplierTypeJLabel.setForeground(Color.red);
        supplierTypeJLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        borderImagePanel.add(supplierTypeJLabel);
        imagePanel.add(borderImagePanel);
        
        
        orderStringJLabel = new JLabel("test");
        orderStringJLabel.setBorder(new MetalBorders.TextFieldBorder());
        
        JLabel orderDetailsJLabel = new JLabel("Details of the order :");
        
        String[] tableTitles = {"Name", "Type", "Quantity"};
        
        JTable orderDetailsJTable = new JTable();
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(tableTitles);
        orderDetailsJTable.setModel(model);

        orderDetailsJTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(orderDetailsJTable);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JButton setAvailibilityJButton = new JButton("Set availibility");
        setAvailibilityJButton.addActionListener(this);
        setAvailibilityJButton.setName("setAvailibilityButton");
        
        JRadioButton availableJRadioButton = new JRadioButton("Available");
        availableJRadioButton.setActionCommand("Available");
        JRadioButton notAvailableJRadioButton = new JRadioButton("Not available");
        notAvailableJRadioButton.setActionCommand("Not available");
        
        bg = new ButtonGroup();
        bg.add(availableJRadioButton);
        bg.add(notAvailableJRadioButton);
        
        JButton sendResponseJButton = new JButton("Send response");
        sendResponseJButton.addActionListener(this);
        sendResponseJButton.setName("sendButton");
        sendResponseJButton.setAlignmentX(CENTER_ALIGNMENT);

        
        JPanel rowPanel1 = new JPanel();
        rowPanel1.setLayout(new GridLayout(0, 3 ,60, 0));
        rowPanel1.add(readPanel);
        rowPanel1.add(pendingPanel);
        rowPanel1.add(imagePanel);
        
        JPanel rowPanel2 = new JPanel();
        rowPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        rowPanel2.add(orderStringJLabel);
        
        JPanel rowPanel3 = new JPanel();
        rowPanel3.setLayout(new BoxLayout(rowPanel3, BoxLayout.X_AXIS));
        rowPanel3.add(orderDetailsJLabel);
        rowPanel3.add(Box.createHorizontalGlue());
        rowPanel3.add(scrollPane);
        
        JPanel rowPanel4 = new JPanel();
        rowPanel4.setLayout(new BoxLayout(rowPanel4, BoxLayout.X_AXIS));
        rowPanel4.setBorder(new EmptyBorder(0, 100, 0, 100));
        rowPanel4.add(setAvailibilityJButton);
        rowPanel4.add(Box.createHorizontalGlue());
        rowPanel4.add(availableJRadioButton);
        rowPanel4.add(Box.createHorizontalGlue());
        rowPanel4.add(notAvailableJRadioButton);
        
        JPanel rowPanel5 = new JPanel();
        rowPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        rowPanel5.add(sendResponseJButton);
        
        mainPanel.add(rowPanel1);
        mainPanel.add(rowPanel2);
        mainPanel.add(rowPanel3);
        mainPanel.add(rowPanel4);
        mainPanel.add(rowPanel5);

        this.add(mainPanel);
    }

}
