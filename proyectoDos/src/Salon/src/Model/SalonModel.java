package Salon.src.Model;

import java.util.*;
import java.net.*;

import Patterns.Observable;



public class SalonModel extends Observable {

    private ArrayList<Boolean> mesas;
    private String cuenta;
    private Socket salonClient;

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

    public Socket getSalonClient() {
        return salonClient;
    }

    public void setSalonClient(Socket salonClient) {
        this.salonClient = salonClient;
    }




    
}
