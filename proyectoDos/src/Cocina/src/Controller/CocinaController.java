package Cocina.src.Controller;

import java.util.Random;

import Cocina.src.Model.CocinaModel;
import Cocina.src.Model.Orden;
import Cocina.src.View.GuiCocina;
import Salon.src.Controller.SalonController;
import SharedClasses.MensajeNotif;
import SharedClasses.MensajeOrden;
import java.net.*;
import java.io.*;
import Patterns.Observable;



public class CocinaController extends Observable{
    private CocinaModel cocinaModel;
    private Random rand;

    public CocinaController(){
        cocinaModel = new CocinaModel();
        rand = new Random(12345678);
        ServerSocket cocinaServer = cocinaModel.getCocinaServer();
        Socket client = cocinaModel.getClient();
        abrirConexion(cocinaServer, client);
    }


    private void ordenLista(int pNumOrden){
        // tal vez sea necesario que haya otra conexion entre observer entre ellos
        Orden orden = cocinaModel.getOrdenesPendientes().get(pNumOrden);
        orden.setLista(true);
        MensajeNotif mensajeNotif = new MensajeNotif();
        ObjectOutputStream output = cocinaModel.getOutput();
        try{
            output = new ObjectOutputStream(cocinaModel.getClient().getOutputStream());
            mensajeNotif.setMensaje(pNumOrden);
            output.writeObject(output);
            output.flush();
            output.close();
            cocinaModel.getClient().close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        


        cocinaModel.getOrdenesPendientes().remove(pNumOrden);
    }

    private void abrirConexion(ServerSocket pCocinaServer, Socket pClient){
        try{
            pCocinaServer = new ServerSocket(Constants.Constants.COCINA_PORT);
            // despues de conectarse mostramos la interfaz para que no se pegue
            // tiene que estar constantemente recibiendo las ordenes nuevas generadas
            GuiCocina guiCocina = new GuiCocina();
            addObserver(guiCocina);
            while(true){
                pClient = pCocinaServer.accept();
                ObjectInputStream input = new ObjectInputStream(pClient.getInputStream());
                MensajeOrden mensaje = (MensajeOrden)input.readObject();
                if(mensaje.getMensajeOrden() != null){
                    System.out.println("Cocina recibio una orden");
                    ingresarOrden(mensaje.getMensajeOrden());
                }
                pClient.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    private void ingresarOrden(Orden pOrden){
        cocinaModel.getOrdenesPendientes().add(pOrden);
        notifyObservers(pOrden);

        
    }



    
    

/*
    public void prepararOrden(){
        // SE TRABAJA CON LA INTERFAZ HAY QUE CAMBIAR ESTO
        int listSize = cocinaModel.getOrdenesPendientes().size();
        // thread para simular la preparacion de las ordenes
        while(true){
            int currentOrder = 0 + rand.nextInt(listSize);
            int prepDuration = 0;
            System.out.println(currentOrder);
            try{
                // puede tardar entre 1 a 9 segundos preparando un pedido
                prepDuration = 1000 + rand.nextInt(9000);
                //System.out.println("prepDuration: " + prepDuration);
                Thread.sleep(prepDuration);
                ordenLista(currentOrder);
                cocinaModel.getOrdenesPendientes().remove(currentOrder);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }


    }
    */


    
}
