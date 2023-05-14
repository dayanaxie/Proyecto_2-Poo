package Salon.src.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataEvent;

// usar los botones para simular las mesas
// que el boton diga "pagar" 
// mesa verde: se puede pagar (generar cuenta) cuando se aprete pagar aparezca la cuenta
// mesa roja ocupada
// mesa blanca esta libre



public class GuiSalon implements ActionListener{
    JFrame ventanaSalon;
    JPanel panelMesas;
    JPanel panelIngresoPedidos;
    

    public GuiSalon(){
        ventanaSalon = new JFrame("Salon del Restaurante");
        ventanaSalon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaSalon.setExtendedState(JFrame.MAXIMIZED_BOTH);
        agregarComponentes();

        ventanaSalon.pack();
        ventanaSalon.setVisible(true);
    }

    public void agregarComponentes(){
        panelMesas = new JPanel();
        panelIngresoPedidos = new JPanel();

        crearMesas();
        ventanaSalon.add(panelMesas, BorderLayout.EAST);
        crearOrdenManual();
        ventanaSalon.add(panelIngresoPedidos, BorderLayout.CENTER);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void crearMesas(){
        int amount = (int)Math.sqrt(Constants.Constants.CANT_MESAS);
        panelMesas.setLayout(new GridLayout(amount,amount));
        for(int index = 0; index < Constants.Constants.CANT_MESAS; ++index) {
            JButton boton = new JButton("Pagar Mesa " + index);
            boton.setBackground(Color.white);
            // para guardar el numero de mesa
            boton.setActionCommand(Integer.toString(index));
            boton.addActionListener(this);
            panelMesas.add(boton);
        }
    }

    private void crearOrdenManual(){
        panelIngresoPedidos.setLayout(new GridLayout(6,1));
        JLabel tittle = new JLabel("Hacer Pedido");
        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        tittle.setForeground(Color.BLUE);
        panelIngresoPedidos.add(tittle);
        String burgPredefList[] = { "Elegir Hamburguesa","Hamburguesa 1", "Hamburguesa 2", "Hamburguesa 3"};
        JComboBox<String> predefBurgsCB = new JComboBox<String>(burgPredefList);
        panelIngresoPedidos.add(predefBurgsCB);
        JLabel labelIngredientes = new JLabel("Ingredientes a agregar:");
        labelIngredientes.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoPedidos.add(labelIngredientes);
        // digamos que solo pueden agegar max 5 de cada ingrediente
        String jamonList[] = { "Elegir Cantidad de Jamon","1 Jamon", "2 Jamon", "3 Jamon", "4 Jamon", "5 Jamon"};
        JComboBox<String> jamonComboBox = new JComboBox<String>(jamonList);
        panelIngresoPedidos.add(jamonComboBox);
        String tortaList[] = { "Elegir Cantidad de Torta","1 Torta", "2 Torta", "3 Torta", "4 Torta", "5 Torta"};
        JComboBox<String> tortaComboBox = new JComboBox<String>(tortaList);
        panelIngresoPedidos.add(tortaComboBox);
        JButton boton = new JButton("Enviar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.red);
        boton.addActionListener(this);
        panelIngresoPedidos.add(boton);
    }

 

    
}
