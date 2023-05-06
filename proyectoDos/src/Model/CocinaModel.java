package Model;

import java.util.*;
import Model.Patterns.Observable;
import Model.Patterns.IObserver;

public class CocinaModel implements IObserver{
    ArrayList<Orden> ordenesPendientes;

    public CocinaModel(){
        ordenesPendientes = new ArrayList<>();
    } 

    private void ordenLista(int pNumOrden){
        Orden orden = ordenesPendientes.get(pNumOrden);
        orden.setLista(true);
        
    }

    
    @Override
    public void update(Observable pObservable, Object args){
        
    }


    //usa un thread para "preparar" las ordenes
    
}
