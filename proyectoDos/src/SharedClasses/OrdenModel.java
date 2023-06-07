package SharedClasses;
import java.io.Serializable;


public class OrdenModel implements Serializable{
    private Boolean lista;
    private int numMesa;
    private Hamburguesa hamburguesa;
    private int precio;

    public OrdenModel(int pNumMesa){
        lista = false;
        numMesa = pNumMesa;
    }

    public OrdenModel(){
        lista = false;
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

    public Hamburguesa getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(Hamburguesa hamburguesa) {
        this.hamburguesa = hamburguesa;
        precio = hamburguesa.getHamburguesa().size() * 200;

    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    
}
