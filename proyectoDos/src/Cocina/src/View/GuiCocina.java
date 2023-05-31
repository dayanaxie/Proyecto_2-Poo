package Cocina.src.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.util.*;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Patterns.*;
import Patterns.Observable;
import Cocina.src.Model.OrdenModel;

import javax.swing.JTextArea;


public class GuiCocina extends Observable implements ActionListener, IObserver{
    JFrame ventanaCocina;
    JPanel panelOrdenes;
    JPanel panelOrdenLista;
    ArrayList<Integer> listOrdenesPend;
    JComboBox<Integer> comboNumOrden;
    JButton listo;

    public GuiCocina(){
        ventanaCocina = new JFrame("Cocina del Restaurante");
        ventanaCocina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaCocina.setExtendedState(JFrame.MAXIMIZED_BOTH);
        agregarComponentes();

        ventanaCocina.pack();
        ventanaCocina.setVisible(true);
    }

    public void agregarComponentes(){
        listOrdenesPend = new ArrayList<>();
        crearPanelOrdenes();
        ventanaCocina.add(panelOrdenes, BorderLayout.CENTER);
        crearPanelSendOrder();
        ventanaCocina.add(panelOrdenLista, BorderLayout.EAST);

        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(listo)){
            if(comboNumOrden.getSelectedItem() != null){
                int numOrden = (int)comboNumOrden.getSelectedItem();
                System.out.println("lista orden: " + numOrden);
                notifyObservers(numOrden);
                //System.out.println("termine de notificar");
                ordenLista(numOrden);
            }
            
        }


    }
    

    @Override
    public void update(Observable pObservable, Object args) {
        ingresarOrden((OrdenModel)args);       
    }

    private void ordenLista(int pNumOrden){
        //System.out.println("ordenLista");
        JTextArea foundTextArea = buscarTextArea(pNumOrden);
        actualizarPanel(foundTextArea);
        //System.out.println("lo elimine");
        for(int num = 0 ; num < listOrdenesPend.size(); ++num){
            if(listOrdenesPend.get(num) == pNumOrden){
                listOrdenesPend.remove(num);
                break;
            }
        }
        // actualizar el comboBox
        actualizarOrdenes();
        // actualizar el panel
        
        
    }

    private JTextArea buscarTextArea(int num){
        //System.out.println("buscarTextArea");
        JTextArea foundTextArea = new JTextArea();
        for (Component componente : panelOrdenes.getComponents()){
            //System.out.println("entro al for");
            JTextArea textArea = (JTextArea) componente;
            //System.out.println(textArea.getToolTipText() + " " + num);
            if (Integer.parseInt(textArea.getToolTipText()) == num) {
                //System.out.println("lo encontre");
                foundTextArea = textArea;
                break;
            }
        }
        return foundTextArea;
    }

    private void ingresarOrden(OrdenModel pOrden){
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        String texto = "Numero de Orden: " + pOrden.getNumMesa() + "\nAqui vamos a mostrar la orden: ";
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(border);
        textArea.setBackground(Color.white); 
        textArea.setToolTipText(Integer.toString(pOrden.getNumMesa()));
        panelOrdenes.add(textArea);
        listOrdenesPend.add(pOrden.getNumMesa());
        actualizarOrdenes();
    }

    private void crearPanelOrdenes(){
        panelOrdenes = new JPanel();
        int amount = (int)Math.sqrt(Constants.Constants.CANT_MESAS);
        panelOrdenes.setLayout(new GridLayout(amount,amount));
        
    }

    private void crearPanelSendOrder(){
        panelOrdenLista = new JPanel();
        panelOrdenLista.setLayout(new GridLayout(3,1));
        JLabel labelChoose = new JLabel("Orden a preparar");
        panelOrdenLista.add(labelChoose);
        Integer ordenPendiente[] = new Integer[Constants.Constants.CANT_MESAS];
        comboNumOrden = new JComboBox<Integer>(ordenPendiente);
        panelOrdenLista.add(comboNumOrden);
        listo = new JButton("Listo");
        listo.setForeground(Color.WHITE);
        listo.setBackground(Color.red);
        listo.addActionListener(this);
        panelOrdenLista.add(listo);
        


    }

    public void actualizarInterfaz(){
        panelOrdenes.validate();
        panelOrdenes.repaint();
    }

 

    private void actualizarOrdenes(){
        comboNumOrden.removeAllItems();
        Collections.sort(listOrdenesPend);
        for(Integer num : listOrdenesPend){
            comboNumOrden.addItem(num);
        }
    }

    private void actualizarPanel(JTextArea foundTextArea){
        panelOrdenes.remove(foundTextArea);

    }

    

 

    
    
    
}
