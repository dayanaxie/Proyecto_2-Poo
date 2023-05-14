package Salon.src.Controller;

import Cocina.src.Model.Orden;
import Patterns.IObserver;
import Patterns.Observable;
import Salon.src.Model.SalonModel;
import java.util.*;
import java.net.*;
import Constants.*;;


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
        try{
            pSalonClient = new Socket("127.0.0.1", Constants.COCINA_PORT);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void update(Observable pObservable, Object args) {
        // va a recibir al numero de mesa, y lo va a liberar y generara la cuenta
        
    }
    public void ingresarOrden(Orden pOrden){
        // este metodo se encarga de mandarle la orden generada a la cocina

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
    
    
}
