package Salon.src.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.util.*;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Cocina.src.Model.Orden;
import Patterns.*;
import Patterns.Observable;


// usar los botones para simular las mesas
// que el boton diga "pagar" 
// mesa verde: se puede pagar (generar cuenta) cuando se aprete pagar aparezca la cuenta
// mesa roja ocupada
// mesa blanca esta libre


// me falta que venga el numero de mesa el pedido


public class GuiSalon extends Observable implements ActionListener{
    JFrame ventanaSalon;
    JPanel panelColores;
    JPanel panelMesas;
    JPanel panelIngresoPedidos;
    JButton botonEnviar;
    ArrayList<JButton> listMesas;
    JComboBox<String> predefBurgsCB;
    JComboBox<String> jamonComboBox;
    JComboBox<String> tortaComboBox;
    JComboBox<String> mesasComboBox;
    
    

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
        panelColores = new JPanel();
        representarColores();
        ventanaSalon.add(panelColores, BorderLayout.NORTH);

        crearMesas();
        ventanaSalon.add(panelMesas, BorderLayout.CENTER);
        crearOrdenManual();
        ventanaSalon.add(panelIngresoPedidos, BorderLayout.WEST);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(botonEnviar)){
            Orden orden = new Orden((Integer)mesasComboBox.getSelectedItem());
            // falta agregarle lo que pidio a la hamburguesa
            notifyObservers(orden);
            
        }

    }

    private void liberarMesa(int pNum){
        JButton boton = listMesas.get(pNum);
        boton.setBackground(Color.white);

    }

    private void generarCuenta(){

    }

    private void representarColores(){
        panelColores.setLayout(new GridLayout(1,3));
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        JLabel mesaLibre = new JLabel("Mesa Disponible");
        mesaLibre.setHorizontalAlignment(SwingConstants.CENTER);
        mesaLibre.setOpaque(true);
        mesaLibre.setBackground(Color.white);
        mesaLibre.setBorder(border);
        JLabel mesaOcupada = new JLabel("Mesa Ocupada");
        mesaOcupada.setHorizontalAlignment(SwingConstants.CENTER);
        mesaOcupada.setOpaque(true);
        mesaOcupada.setBackground(Color.red);
        mesaOcupada.setBorder(border);
        JLabel mesaFacturar = new JLabel("Mesa a Facturar");
        mesaFacturar.setHorizontalAlignment(SwingConstants.CENTER);
        mesaFacturar.setOpaque(true);
        mesaFacturar.setBackground(Color.green);
        mesaFacturar.setBorder(border);
        panelColores.add(mesaOcupada);
        panelColores.add(mesaLibre);
        panelColores.add(mesaFacturar);
    }


    private void crearMesas(){
        int amount = (int)Math.sqrt(Constants.Constants.CANT_MESAS);
        listMesas = new ArrayList<>();
        panelMesas.setLayout(new GridLayout(amount,amount));
        for(int index = 0; index < Constants.Constants.CANT_MESAS; ++index) {
            JButton boton = new JButton("Pagar Mesa " + index);
            boton.setBackground(Color.white);
            // para guardar el numero de mesa
            boton.setActionCommand(Integer.toString(index));
            boton.addActionListener(this);

            listMesas.add(index, boton);
            panelMesas.add(boton);
        }
    }

    private void crearOrdenManual(){
        panelIngresoPedidos.setLayout(new GridLayout(8,1));
        JLabel tittle = new JLabel("Hacer Pedido");
        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        tittle.setForeground(Color.BLUE);
        panelIngresoPedidos.add(tittle);
        pedidoPredef();
        pedidoPers();
        pedirNumeroMesa();
        
        botonEnviar = new JButton("Enviar");
        botonEnviar.setForeground(Color.WHITE);
        botonEnviar.setBackground(Color.red);
        botonEnviar.addActionListener(this);
        panelIngresoPedidos.add(botonEnviar);
    }

    private void pedidoPredef(){
        String burgPredefList[] = { "Elegir Hamburguesa","Hamburguesa 1", "Hamburguesa 2", "Hamburguesa 3"};
        predefBurgsCB = new JComboBox<String>(burgPredefList);
        panelIngresoPedidos.add(predefBurgsCB);
    }

    private void pedidoPers(){
        JLabel labelIngredientes = new JLabel("Ingredientes a agregar:");
        labelIngredientes.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoPedidos.add(labelIngredientes);
        // digamos que solo pueden agegar max 5 de cada ingrediente
        String jamonList[] = { "Elegir Cantidad de Jamon","1 Jamon", "2 Jamon", "3 Jamon", "4 Jamon", "5 Jamon"};
        jamonComboBox = new JComboBox<String>(jamonList);
        panelIngresoPedidos.add(jamonComboBox);
        String tortaList[] = { "Elegir Cantidad de Torta","1 Torta", "2 Torta", "3 Torta", "4 Torta", "5 Torta"};
        tortaComboBox = new JComboBox<String>(tortaList);
        panelIngresoPedidos.add(tortaComboBox);
    }

    private void pedirNumeroMesa(){
        JLabel labelNumMesa = new JLabel("Elegir mesa:");
        labelNumMesa.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoPedidos.add(labelNumMesa);
        String mesasDisponibles[] = mesasDisponibles();
        mesasComboBox = new JComboBox<String>(mesasDisponibles);
        panelIngresoPedidos.add(mesasComboBox);
    }

    private String [] mesasDisponibles(){
        String mesasDisponibles[] = new String[Constants.Constants.CANT_MESAS];
        int index = 0;
        for(JButton boton : listMesas){
            if(boton.getBackground() == Color.WHITE){
                mesasDisponibles[index] = boton.getActionCommand();
                ++index;
                }
        }
        return mesasDisponibles;
    }

 

    
}
