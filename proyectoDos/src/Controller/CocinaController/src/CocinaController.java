package Controller.CocinaController.src;

import Model.CocinaModel;
import Model.Orden;
import Model.Patterns.*;
import java.util.Random;

import Controller.SalonController.src.SalonController;


public class CocinaController extends Observable {
    private CocinaModel cocinaModel;
    private Random rand;

    public CocinaController(SalonController pSalonController){
        cocinaModel = new CocinaModel();
        rand = new Random(12345678);
        Server salonServer = cocinaModel.getSalonServer();
        salonServer = new Server();
        addObserver(pSalonController);
    }


    private void ordenLista(int pNumOrden){
        Orden orden = cocinaModel.getOrdenesPendientes().get(pNumOrden);
        orden.setLista(true);
        notifyObservers(orden);
    }


    
    


    public void prepararOrden(){
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


    
}
