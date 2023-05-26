package Cocina.src.Model;
import java.io.Serializable;


public class Orden implements Serializable{
    private Boolean lista;
    private int numMesa;
    // tiene que tener la hamburguesa

    public Orden(int pNumMesa){
        lista = false;
        numMesa = pNumMesa;
    }

    public Boolean getLista() {
        return lista;
    }

    public void setLista(Boolean lista) {
        this.lista = lista;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    
    
}
