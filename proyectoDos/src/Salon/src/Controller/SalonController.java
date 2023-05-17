package Salon.src.Controller;

import Cocina.src.Model.Orden;
import Patterns.Observable;
import Patterns.*;

import Salon.src.Model.SalonModel;
import Salon.src.View.GuiSalon;
import SharedClasses.MensajeOrden;

import java.util.*;
import java.net.*;
import java.io.*;


import Constants.*;

// tengo que preguntar si debe de haber una conexion entre
// cocina y salon, xq salon le debe de mandar las ordenes ingresadas a cocina

public class SalonController implements IObserver{
    private SalonModel salonModel;

    public SalonController(){
        salonModel = new SalonModel();
        Socket salonClient = salonModel.getSalonClient();
        conectar(salonClient);
    }

    public void conectar(Socket pSalonClient){
        //esto tengo que ponerle mas detalle
        while(true){
            try{ 
                GuiSalon guiSalon = new GuiSalon();
                guiSalon.addObserver(this);
                if(salonModel.getOutput() != null){
                    ObjectOutputStream output = salonModel.getOutput();
                    output.flush();
                    salonModel.setOutput(null);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
    }

    
    public void ingresarOrden(Orden pOrden){
        // este metodo se encarga de mandarle la orden generada a la cocina
        ObjectOutputStream output = salonModel.getOutput();
        try{
            output = new ObjectOutputStream(salonModel.getSalonClient().getOutputStream());
            MensajeOrden mensaje = new MensajeOrden();
            mensaje.setMensajeOrden(pOrden);
            output.writeObject(mensaje);
            salonModel.setOutput(output);

        }catch(Exception e){
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
        ingresarOrden((Orden)args);
        
    }

    
    
}
