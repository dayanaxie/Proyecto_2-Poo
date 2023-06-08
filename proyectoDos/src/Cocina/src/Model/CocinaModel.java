package Cocina.src.Model;

import java.util.*;

import Cocina.src.View.GuiCocina;
import SharedClasses.OrdenModel;

import java.net.*;
import java.io.*;
public class CocinaModel{
    private ArrayList<OrdenModel> ordenesPendientes;
    private ServerSocket cocinaServer;
    private Socket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private GuiCocina guiCocina;

    public CocinaModel(){
        ordenesPendientes = new ArrayList<>(Constants.Constants.CANT_MESAS);
    }

    public ArrayList<OrdenModel> getOrdenesPendientes() {
        return ordenesPendientes;
    }

    public void setOrdenesPendientes(ArrayList<OrdenModel> ordenesPendientes) {
        this.ordenesPendientes = ordenesPendientes;
    }

    public ServerSocket getCocinaServer() {
        return cocinaServer;
    }

    public void setCocinaServer(ServerSocket cocinaServer) {
        this.cocinaServer = cocinaServer;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
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

    public GuiCocina getGuiCocina() {
        return guiCocina;
    }

    public void setGuiCocina(GuiCocina guiCocina) {
        this.guiCocina = guiCocina;
    }



    
    
}
