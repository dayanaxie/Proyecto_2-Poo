package Salon.src.Model;

import java.util.*;
import java.net.*;
import java.io.*;


import Patterns.Observable;
import Salon.src.View.*;;



public class SalonModel extends Observable {
    private Random rand;
    private ArrayList<Boolean> mesas;
    private ServerSocket salonPort;
    private ServerSocket salonPort2;
    private Socket clientAcceptCocina;
    private Socket clientAcceptSimulacion;
    private Socket clientConnect;

    private ObjectOutputStream output;
    private ObjectInputStream inputCocina;
    private ObjectInputStream inputSimulacion;
    GuiSalon guiSalon;

    public SalonModel(){
        mesas = new ArrayList<Boolean>();
        rand = new Random(134564);
        
    }

    public ArrayList<Boolean> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Boolean> mesas) {
        this.mesas = mesas;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

   


    public ObjectInputStream getInputCocina() {
        return inputCocina;
    }

    public void setInputCocina(ObjectInputStream inputCocina) {
        this.inputCocina = inputCocina;
    }

    public ObjectInputStream getInputSimulacion() {
        return inputSimulacion;
    }

    public void setInputSimulacion(ObjectInputStream inputSimulacion) {
        this.inputSimulacion = inputSimulacion;
    }

    public GuiSalon getGuiSalon() {
        return guiSalon;
    }

    public void setGuiSalon(GuiSalon guiSalon) {
        this.guiSalon = guiSalon;
    }



    public Socket getClientConnect() {
        return clientConnect;
    }

    public void setClientConnect(Socket clientConnect) {
        this.clientConnect = clientConnect;
    }

    public ServerSocket getSalonPort() {
        return salonPort;
    }

    public void setSalonPort(ServerSocket salonPort) {
        this.salonPort = salonPort;
    }

    public ServerSocket getSalonPort2() {
        return salonPort2;
    }

    public void setSalonPort2(ServerSocket salonPort2) {
        this.salonPort2 = salonPort2;
    }

    public Socket getClientAcceptCocina() {
        return clientAcceptCocina;
    }

    public void setClientAcceptCocina(Socket clientAcceptCocina) {
        this.clientAcceptCocina = clientAcceptCocina;
    }

    public Socket getClientAcceptSimulacion() {
        return clientAcceptSimulacion;
    }

    public void setClientAcceptSimulacion(Socket clientAcceptSimulacion) {
        this.clientAcceptSimulacion = clientAcceptSimulacion;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }




    
}
