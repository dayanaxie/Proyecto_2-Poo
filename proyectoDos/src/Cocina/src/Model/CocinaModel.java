package Cocina.src.Model;

import java.util.*;
import java.net.*;
import java.io.*;
public class CocinaModel{
    ArrayList<Orden> ordenesPendientes;
    private ServerSocket cocinaServer;
    private Socket client;
    private ObjectOutputStream output;

    public CocinaModel(){
        ordenesPendientes = new ArrayList<>(Constants.Constants.CANT_MESAS);
    }

    public ArrayList<Orden> getOrdenesPendientes() {
        return ordenesPendientes;
    }

    public void setOrdenesPendientes(ArrayList<Orden> ordenesPendientes) {
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

    
    
}
