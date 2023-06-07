package SharedClasses;

import java.io.Serializable;
import java.util.*;

//este es para cuando salon tiene que pasarle las ordenes a cocina


public class MensajeOrden implements Serializable{
    OrdenModel msjOrden;

    public MensajeOrden(){
 
    }

    public void setMensajeOrden(OrdenModel pOrden){
        msjOrden = pOrden;

    }

    public OrdenModel getMensajeOrden(){
        return msjOrden;
    }

    public void mostrarOrden(){
        System.out.println("Ingredientes de la hamburguesa: ");
        for(String ingrediente : msjOrden.getHamburguesa().getHamburguesa()){
            System.out.println(ingrediente);
        }
        
    }





}



