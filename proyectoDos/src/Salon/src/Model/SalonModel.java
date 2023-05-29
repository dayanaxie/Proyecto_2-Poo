package Salon.src.Model;

import java.util.*;
import java.net.*;
import java.io.*;
import Constants.*;


import Patterns.Observable;
import Salon.src.View.*;;



public class SalonModel extends Observable {
    // creo que los models deberian de tener los gui

    private ArrayList<Boolean> mesas;
    private String cuenta;
    private ServerSocket salonServer;
    private Socket clientAccept;
    private Socket clientConnect;

    private ObjectOutputStream output;
    private ObjectInputStream input;
    GuiSalon guiSalon;

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

    public ServerSocket getSalonServer() {
        return salonServer;
    }

    public void setSalonServer(ServerSocket salonServer) {
        this.salonServer = salonServer;
    }

    public GuiSalon getGuiSalon() {
        return guiSalon;
    }

    public void setGuiSalon(GuiSalon guiSalon) {
        this.guiSalon = guiSalon;
    }

    public Socket getClientAccept() {
        return clientAccept;
    }

    public void setClientAccept(Socket clientAccept) {
        this.clientAccept = clientAccept;
    }

    public Socket getClientConnect() {
        return clientConnect;
    }

    public void setClientConnect(Socket clientConnect) {
        this.clientConnect = clientConnect;
    }




    
}
