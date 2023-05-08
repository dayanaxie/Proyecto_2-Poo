package Controller.CocinaController.src;

import Model.CocinaModel;
import Model.Orden;
import Model.Patterns.*;
import java.util.Random;


public class CocinaController implements IObserver{
    private CocinaModel cocinaModel;
    private Random rand;

    public CocinaController(){
        cocinaModel = new CocinaModel();
        rand = new Random(12345678);
    }


    private void ordenLista(int pNumOrden){
        Orden orden = cocinaModel.getOrdenesPendientes().get(pNumOrden);
        orden.setLista(true);
        
    }

    
    @Override
    public void update(Observable pObservable, Object args){
        
    }


    public void prepararOrden(){
        int listSize = cocinaModel.getOrdenesPendientes().size();
        // thread para simular la preparacion de las ordenes
        while(true){
            int currentOrder = 0 + rand.nextInt(listSize);
            int prepDuration = 0;
            System.out.println(currentOrder);
            try{
                // puede tardar entre 1 a 10 segundos preparando un pedido
                prepDuration = 1000 + rand.nextInt(9000);
                System.out.println("prepDuration: " + prepDuration);
                Thread.sleep(prepDuration);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }


    }


    
}
