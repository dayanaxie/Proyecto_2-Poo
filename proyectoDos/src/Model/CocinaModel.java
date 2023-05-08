package Model;

import java.util.*;

public class CocinaModel{
    ArrayList<Orden> ordenesPendientes;

    public CocinaModel(){
        ordenesPendientes = new ArrayList<>();
        

    }

    public ArrayList<Orden> getOrdenesPendientes() {
        return ordenesPendientes;
    }

    public void setOrdenesPendientes(ArrayList<Orden> ordenesPendientes) {
        this.ordenesPendientes = ordenesPendientes;
    } 

    
    
}
