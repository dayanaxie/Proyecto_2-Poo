package SharedClasses;
import java.io.Serializable;

import java.util.*;

public class Hamburguesa implements Serializable{
    ArrayList<String> hamburguesa;
    String nombre;


    public Hamburguesa(){

    }

    public void crearHamburguesa(String hambur){
        ArrayList<String> ingredientes = new ArrayList<String>();
        switch(hambur){
            case "Hamburguesa doble torta":
                ingredientes.add("Pan");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
            case "Hamburguesa con bacon":
                ingredientes.add("Pan");
                ingredientes.add("Salsa BBQ");
                ingredientes.add("Bacon");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
            case "Hamburguesa con queso":
                ingredientes.add("Pan");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
            case "Hamburguesa Especial":
                ingredientes.add("Pan");
                ingredientes.add("Tomate");
                ingredientes.add("Lechuga");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Bacon");
                ingredientes.add("Aguacate");
                ingredientes.add("Cebolla");
                ingredientes.add("Torta");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
            case "Hamburguesa Clasica":
                ingredientes.add("Pan");
                ingredientes.add("Tomate");
                ingredientes.add("Lechuga");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Cebolla");
                ingredientes.add("Salsa de tomate");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
            case "Hamburguesa Base":
                ingredientes.add("Pan");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                break;
        }
        this.setNombre(hambur);
        this.setHamburguesa(ingredientes);
    }


    public String getIngredientes(){
        String ingredientes = "";
        for(String string : hamburguesa){
            ingredientes += string + ", ";
        }
        return ingredientes;
    }

    public ArrayList<String> getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(ArrayList<String> hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
