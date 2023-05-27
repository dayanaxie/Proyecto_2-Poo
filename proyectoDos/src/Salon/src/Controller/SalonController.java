package Salon.src.Controller;

import Cocina.src.Model.Orden;
import Patterns.Observable;
import Patterns.*;

import Salon.src.Model.SalonModel;
import Salon.src.View.GuiSalon;
import SharedClasses.MensajeNotif;
import SharedClasses.MensajeOrden;

import java.util.*;
import java.net.*;
import java.io.*;


import Constants.*;

// tengo que preguntar si debe de haber una conexion entre
// cocina y salon, xq salon le debe de mandar las ordenes ingresadas a cocina

public class SalonController extends Observable implements IObserver{
    private SalonModel salonModel;

    public SalonController(){
        salonModel = new SalonModel();
        Thread abrirServer = new Thread(() -> abrirConexion());
        abrirServer.start();
        Thread conectarServer = new Thread(() -> conectar());
        conectarServer.start();
    }

    public void conectar(){
        
        // este es para conectar con cocina
        try{
            Socket salonClient = new Socket("127.0.0.1", Constants.COCINA_PORT);
            salonModel.setSalonClient(salonClient);
            mostrarInterfaz();
            while(true){
                //System.out.println("Intentando conectar");
                //System.out.println("no llega aqui");
                if(salonModel.getOutput() != null){    
                    //System.out.println("deberia de etrar aqui");
                    ObjectOutputStream output = salonModel.getOutput();
                    //System.out.println("lo mando");
                    output.flush();
                    //System.out.println("reseteo");
                    salonModel.setOutput(null);
                    
                }
                
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
    }


    private void abrirConexion(){
        // este es para que cocina se conecte a salon
        try{
            System.out.println("Se abrio SALON_PORT2");
            ServerSocket salonServer = new ServerSocket(Constants.SALON_PORT2);
            System.out.println("Salon esta esperando conexion");
            Socket client = salonServer.accept();
            System.out.println("SE CONECTOOOOOOOOOOOOOOOOOOOOO");
            salonModel.setSalonClient(client);
            salonModel.setSalonServer(salonServer);
            while(true){
                if(client.getInputStream().available() > 0){                    
                    System.out.println("Salon recibió la notificación de facturar la mesa: " );
                    ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                    MensajeNotif mensaje = (MensajeNotif) input.readObject();
                    System.out.println(mensaje.getMensaje());
                    notifyObservers(mensaje.getMensaje());
                    salonModel.setInput(null);
                    // como cocina se desconecta despues de mandar el input hay que volverlo a aceptar
                    client = salonServer.accept();
                    System.out.println("SE CONECTOOOOOOOOOOOOOOOOOOOOO");
                    salonModel.setSalonClient(client);
                    salonModel.setSalonServer(salonServer);
                }
                
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    
    public void ingresarOrden(Orden pOrden){
        // este metodo se encarga de mandarle la orden generada a la cocina
        try{
            System.out.println("1" );
            ObjectOutputStream output = new ObjectOutputStream(salonModel.getSalonClient().getOutputStream());
            System.out.println("2" );
            MensajeOrden mensaje = new MensajeOrden();
            mensaje.setMensajeOrden(pOrden);
            System.out.println("3" );
            output.writeObject(mensaje);
            System.out.println("4" );
            salonModel.setOutput(output);
            System.out.println("Se ingreso la orden !!!!!!" );
        }catch(Exception e){
            System.out.println("El problema esta en ingresar orden" );
            System.out.println(e);
        }
        

    }

    public String generarCuenta(){
        return salonModel.getCuenta();

        // va a devolver un string con las hamburguesas pedidas y su precio y el
        // total como un string para mostrar en la interfaz
    }

    public void liberarMesa(){
        //puede haber un arraylist de booleanos y que el index del array sea el 
        // numero de mesa

    }

    public void crearOrdenManual(){
        // esto tengo que pensarlo mas
    }

    
    @Override
    public void update(Observable pObservable, Object args) {
        //System.out.println("llego a update");
        ingresarOrden((Orden)args);
        
    }

    private void mostrarInterfaz(){
        GuiSalon guiSalon = new GuiSalon();
        guiSalon.addObserver(this);
        addObserver(guiSalon);

    }

    
    
}
