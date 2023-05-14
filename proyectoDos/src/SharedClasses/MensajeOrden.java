package SharedClasses;

import java.io.Serializable;
import Cocina.src.Model.Orden;

//este es para cuando salon tiene que pasarle las ordenes a cocina


public class MensajeOrden implements Serializable{
    Orden msjOrden;

    public MensajeOrden(){
 
    }

    public void setMensajeOrden(Orden pOrden){
        msjOrden = pOrden;

    }

    public Orden getMensajeOrden(){
        return msjOrden;
    }



}



