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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Patterns.*;
import Patterns.Observable;
import SharedClasses.Hamburguesa;
import SharedClasses.OrdenModel;


// mesa verde: se puede pagar (generar cuenta) cuando se aprete pagar aparezca la cuenta
// mesa roja ocupada
// mesa blanca esta libre

public class GuiSalon extends Observable implements ActionListener, IObserver{
    JFrame ventanaSalon;
    JPanel panelColores;
    JPanel panelMesas;
    JPanel panelIngresoPedidos;
    JButton botonEnviar;
    ArrayList<JButton> listMesas;
    JComboBox<String> predefBurgsCB;
    JComboBox<String> extra1ComboBox;
    JComboBox<String> extra2ComboBox;
    JComboBox<Integer> mesasComboBox;
    

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
            // para enviar la orden manual creada
            if(mesasComboBox.getSelectedItem() != null){
                Hamburguesa hamburguesa = new Hamburguesa();
                hamburguesa.crearHamburguesa((String)predefBurgsCB.getSelectedItem());
                if((String)extra1ComboBox.getSelectedItem() != "Elegir extra"){
                    hamburguesa.getHamburguesa().add((String)extra1ComboBox.getSelectedItem());
                }if ((String)extra2ComboBox.getSelectedItem() != "Elegir extra"){
                    hamburguesa.getHamburguesa().add((String)extra2ComboBox.getSelectedItem());
                }
                OrdenModel orden = new OrdenModel((int)mesasComboBox.getSelectedItem());

                orden.setHamburguesa(hamburguesa);
                notifyObservers(orden, false);
                // se actualiza la interfaz
                mesaOcupada(orden.getNumMesa());
                guardarFactura(orden.getNumMesa(), orden.getPrecio());
                updateNumeroMesa(); //esto es para actualizar las mesas disponibles
            }
            
        }else{

            JButton button = (JButton) e.getSource();
            Color color = button.getBackground();
            if(color == Color.green){
                // si se presiona el boton para facturar la mesa
                ImageIcon imageIcon = new ImageIcon("Imagen/cubo.png");
                Image image = imageIcon.getImage();
                Image resizedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                Icon icon = new ImageIcon(resizedImage);
                String string = button.getName();
                Object[] options = {"Pagar"};
                int option = JOptionPane.showOptionDialog(ventanaSalon,
                string,"Factura",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                options[0]);

                // una vez pagado se librera la mesa
                liberarMesa(button);
                updateNumeroMesa();
                notifyObservers(Integer.parseInt(button.getActionCommand()), true);

            }

        }

    }

    @Override
    public void update(Observable pObservable, Object args, Object flag) {
        
        if(flag instanceof Integer){
            // flag seria la factura de la mesa
            mesaOcupada((int)args);
            guardarFactura((int)args, (int)flag);
            updateNumeroMesa();
        }else{
            // sino entonces seria un booleano y se manda a facturar la mesa
            mesaFacturar((int)args);
        }
    }

    private void guardarFactura(int pNum, int pFactura){
        JButton boton = listMesas.get(pNum);
        boton.setName("₡ " + Integer.toString(pFactura));
    }

    private void liberarMesa(JButton pButton){
        pButton.setBackground(Color.white);
        pButton.setName(null);
    }

    private void mesaOcupada(int pNum){
        JButton boton = listMesas.get(pNum);
        boton.setBackground(Color.red);
        
    }

    private void mesaFacturar(int pNum){
        JButton boton = listMesas.get(pNum);
        boton.setBackground(Color.green);
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
            JButton boton = new JButton("Mesa " + index);
            boton.setBackground(Color.white);
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
        String burgPredefList[] = { "Elegir Hamburguesa","Hamburguesa doble torta", "Hamburguesa con bacon", "Hamburguesa con queso", "Hamburguesa Especial", "Hamburguesa Clasica", "Hamburguesa Base"};
        predefBurgsCB = new JComboBox<String>(burgPredefList);
        panelIngresoPedidos.add(predefBurgsCB);
    }

    private void pedidoPers(){
        JLabel labelIngredientes = new JLabel("Ingredientes a agregar:");
        labelIngredientes.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoPedidos.add(labelIngredientes);
        String extra1List[] = { "Elegir extra","pepinillos", "torta", "queso", "salsa ranch", "tomate", "aguacate", "cebolla", "jalapeños"};
        extra1ComboBox = new JComboBox<String>(extra1List);
        panelIngresoPedidos.add(extra1ComboBox);
        String extra2List[] = { "Elegir extra","pepinillos", "torta", "queso", "salsa ranch", "tomate", "aguacate", "cebolla", "jalapeños"};
        extra2ComboBox = new JComboBox<String>(extra2List);
        panelIngresoPedidos.add(extra2ComboBox);
    }

    private void pedirNumeroMesa(){
        JLabel labelNumMesa = new JLabel("Elegir mesa:");
        labelNumMesa.setHorizontalAlignment(SwingConstants.CENTER);
        panelIngresoPedidos.add(labelNumMesa);
        Integer mesasDisponibles[] = mesasDisponibles();
        mesasComboBox = new JComboBox<Integer>(mesasDisponibles);
        panelIngresoPedidos.add(mesasComboBox);
    }

    private void updateNumeroMesa(){
        mesasComboBox.removeAllItems();
        Integer mesasDisponibles[] = mesasDisponibles();
        for(Integer num : mesasDisponibles){
            mesasComboBox.addItem(num);
        }
    }

    private Integer [] mesasDisponibles(){
        // obtener las mesas disponibles actualmente
        Integer mesasDisponibles[] = new Integer[Constants.Constants.CANT_MESAS];
        int index = 0;
        for(JButton boton : listMesas){
            if(boton.getBackground() == Color.WHITE){
                mesasDisponibles[index] = Integer.parseInt(boton.getActionCommand());
                ++index;
                }
        }
        return mesasDisponibles;
    }


}
