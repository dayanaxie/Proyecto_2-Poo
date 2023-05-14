package SharedClasses;
import java.io.Serializable;

// este es cuando cocina tiene que mandarle a salon el numero de mesa que tiene que liberar

public class MensajeNotif implements Serializable{
    int msjNumMesa;

    public MensajeNotif(){
 
    }

    public void setMensaje(int pNumMesa){
        msjNumMesa = pNumMesa;

    }

    public int getMensaje(){
        return msjNumMesa;
    }



}

