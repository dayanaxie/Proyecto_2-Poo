package Cocina.src.Controller;


import Cocina.src.Model.CocinaModel;
import Cocina.src.Model.OrdenModel;
import Cocina.src.View.GuiCocina;
import SharedClasses.MensajeNotif;
import SharedClasses.MensajeOrden;
import java.net.*;
import Patterns.*;

import java.io.*;
import Patterns.Observable;



public class CocinaController extends Observable implements IObserver{
    private CocinaModel cocinaModel;

    public CocinaController(){
        cocinaModel = new CocinaModel();
        abrirConexion();
    }

    @Override
    public void update(Observable pObservable, Object args) {
        //System.out.println("llegue a notificar luego de listo");
        conectar((int)args);
        //ordenLista((int)args);       
    }


    public void conectar(int pNumOrden){
        //System.out.println("cocina intentando conectar");
        // este es para conectar con salon

        // esto realmente creo que se podria quitar
        OrdenModel orden = buscarOrden(pNumOrden);
        orden.setLista(true); 
        try{
            Socket cocinaClient = new Socket("127.0.0.1", Constants.Constants.SALON_PORT2);
            //System.out.println("me contecte");
            cocinaModel.setClient(cocinaClient);
            ObjectOutputStream output = new ObjectOutputStream(cocinaModel.getClient().getOutputStream());
            MensajeNotif mensajeNotif = new MensajeNotif();
            mensajeNotif.setMensaje(pNumOrden);
            output.writeObject(mensajeNotif);  
            cocinaModel.setOutput(output);      
            if(cocinaModel.getOutput() != null){    
                //cocinaModel.getOutput().flush();
                //System.out.println("lo mando");
                output.flush();
                //System.out.println("reseteo");
                cocinaModel.setOutput(null);
            }
            output.close();
            cocinaClient.close();

            
        }catch(Exception e){
            System.out.println(e);
        }   
    }


    private OrdenModel buscarOrden(int pNumOrden){
        OrdenModel ordenEncontrada = new OrdenModel(pNumOrden);
        for(OrdenModel orden : cocinaModel.getOrdenesPendientes()){
            if(orden.getNumMesa() == pNumOrden){
                ordenEncontrada = orden;
                break;
            }
        }
        return ordenEncontrada;
    }


    private void eliminarOrden(int pNumOrden){
        OrdenModel orden = buscarOrden(pNumOrden);
        cocinaModel.getOrdenesPendientes().remove(orden);
    }

    private void abrirConexion(){
        // este es para que salon se conecte a cocina
        try{
            
            // despues de conectarse mostramos la interfaz para que no se pegue
            // tiene que estar constantemente recibiendo las ordenes nuevas generadas
            System.out.println("Se abrio COCINA_PORT");
            ServerSocket cocinaServer = new ServerSocket(Constants.Constants.COCINA_PORT);
            System.out.println("Cocina esta esperando conexion");
            Socket client = cocinaServer.accept();
            cocinaModel.setClient(client);
            cocinaModel.setCocinaServer(cocinaServer);
            
            mostrarInterfaz();
            while(true){
                if (client.getInputStream().available() > 0) {
                    //System.out.println("recibi la orden");
                    ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                    //System.out.println("aqui1");
                    MensajeOrden mensaje = (MensajeOrden) input.readObject();
                    //System.out.println("aqui2");
                    System.out.println("Cocina recibió una orden num: " + mensaje.getMensajeOrden().getNumMesa());
                    ingresarOrden(mensaje.getMensajeOrden());
                    cocinaModel.setInput(null);
                    
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void ingresarOrden(OrdenModel pOrden){
        //System.out.println(pOrden.getNumMesa() + " " + cocinaModel.getOrdenesPendientes().size());
        cocinaModel.getOrdenesPendientes().add(pOrden);
        notifyObservers(pOrden);

        
    }

    private void mostrarInterfaz(){
        GuiCocina guiCocina = new GuiCocina();
        addObserver(guiCocina);
        guiCocina.addObserver(this);

    }



    
    


    
}