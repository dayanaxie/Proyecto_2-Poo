package Cocina.src.Controller;


import Cocina.src.Model.CocinaModel;
import Cocina.src.View.GuiCocina;
import SharedClasses.MensajeNotif;
import SharedClasses.MensajeOrden;
import SharedClasses.OrdenModel;

import java.net.*;
import Patterns.*;

import java.io.*;
import Patterns.Observable;



public class CocinaController extends Observable implements IObserver{
    private CocinaModel cocinaModel;

    public CocinaController(){
        System.out.println("--------------------COCINA--------------------");
        cocinaModel = new CocinaModel();
        abrirConexion();
    }

    @Override
    public void update(Observable pObservable, Object args, Object flag) {
        conectar((int)args);
    }
   


    public void conectar(int pNumOrden){
        // este es para conectar con salon
        OrdenModel orden = buscarOrden(pNumOrden);
        orden.setLista(true); 
        try{
            Socket cocinaClient = new Socket("127.0.0.1", Constants.Constants.SALON_PORT2);
            cocinaModel.setClient(cocinaClient);
            ObjectOutputStream output = new ObjectOutputStream(cocinaModel.getClient().getOutputStream());
            MensajeNotif mensajeNotif = new MensajeNotif();
            mensajeNotif.setMensaje(pNumOrden);
            output.writeObject(mensajeNotif);  
            cocinaModel.setOutput(output);      
            if(cocinaModel.getOutput() != null){    
                output.flush();
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

    private void abrirConexion(){
        // este es para que salon se conecte a cocina
        try{
            System.out.println("Se abrio COCINA_PORT");
            ServerSocket cocinaServer = new ServerSocket(Constants.Constants.COCINA_PORT);
            System.out.println("Cocina esta esperando conexion");
            Socket client = cocinaServer.accept();
            cocinaModel.setClient(client);
            cocinaModel.setCocinaServer(cocinaServer);
            mostrarInterfaz();
            while(true){
                if (client.getInputStream().available() > 0) {
                    ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                    MensajeOrden mensaje = (MensajeOrden) input.readObject();
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
        cocinaModel.getOrdenesPendientes().add(pOrden);
        notifyObservers(pOrden, false);
    }

    private void mostrarInterfaz(){
        GuiCocina guiCocina = cocinaModel.getGuiCocina();
        guiCocina = new GuiCocina();
        addObserver(guiCocina);
        guiCocina.addObserver(this);

    }



    
    


    
}