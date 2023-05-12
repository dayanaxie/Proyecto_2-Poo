package Salon.src.Model;

import Sockets.Client;

import java.util.*;

import Patterns.Observable;



public class SalonModel extends Observable {

    private ArrayList<Boolean> mesas;
    private String cuenta;
    private Client cocinaClient;

    public SalonModel(){
        mesas = new ArrayList<Boolean>();
        cuenta = "";
    }

    public ArrayList<Boolean> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Boolean> mesas) {
        this.mesas = mesas;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Client getCocinaClient() {
        return cocinaClient;
    }

    public void setCocinaClient(Client cocinaClient) {
        this.cocinaClient = cocinaClient;
    }


    
}
