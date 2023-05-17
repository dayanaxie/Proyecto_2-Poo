package Cocina.src.View;
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
import Patterns.*;
import Patterns.Observable;
import Cocina.src.Model.Orden;


public class GuiCocina implements ActionListener, IObserver{
    JFrame ventanaCocina;
    JPanel panelOrdenes;
    JPanel panelOrdenLista;
    ArrayList<JButton> listMesas;

    public GuiCocina(){
        ventanaCocina = new JFrame("Cocina del Restaurante");
        ventanaCocina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCocina.setExtendedState(JFrame.MAXIMIZED_BOTH);
        agregarComponentes();

        ventanaCocina.pack();
        ventanaCocina.setVisible(true);
    }

    public void agregarComponentes(){
        crearPanelOrdenes();

        ventanaCocina.add(panelOrdenes, BorderLayout.NORTH);

        
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    

    @Override
    public void update(Observable pObservable, Object args) {
        // la orden va a tener un metodo para  que pueda acceder a los ingredientes que tiene
        // y asi agregarlo al panel 
        //String orden = (Orden)args.getHamburguesa().getIngredientes;
       
        
        
    }

    private void crearPanelOrdenes(){
        panelOrdenes = new JPanel();
        int amount = (int)Math.sqrt(Constants.Constants.CANT_MESAS);
        panelOrdenes.setLayout(new GridLayout(amount,amount));
        ventanaCocina.add(panelOrdenes, BorderLayout.CENTER);
    }

    private void crearPanelSendOrder(){
        panelOrdenLista = new JPanel();


        JButton boton = new JButton("Enviar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.red);
        boton.addActionListener(this);
        panelOrdenLista.add(boton);
        ventanaCocina.add(panelOrdenLista, BorderLayout.EAST);


    }

    private void ingresarOrden(Orden pOrden){

    }
    
    
}
