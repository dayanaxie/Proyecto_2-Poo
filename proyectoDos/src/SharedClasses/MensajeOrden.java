package SharedClasses;

import java.io.Serializable;
import Cocina.src.Model.OrdenModel;

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



}



