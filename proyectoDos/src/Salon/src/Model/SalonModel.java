package Salon.src.Model;

import java.util.*;
import java.net.*;
import java.io.*;
import Constants.*;


import Patterns.Observable;



public class SalonModel extends Observable {
    // creo que los models deberian de tener los gui

    private ArrayList<Boolean> mesas;
    private String cuenta;
    private Socket salonClient;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public SalonModel(){
        mesas = new ArrayList<Boolean>();
        cuenta = "";
        try{
            salonClient = new Socket("127.0.0.1", Constants.COCINA_PORT);
        }catch(Exception e){
            System.out.println(e);
        }
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

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }




    
}
