package Cocina.src.Model;

import java.util.*;
import java.net.*;


public class CocinaModel{
    ArrayList<Orden> ordenesPendientes;
    private ServerSocket cocinaServer;
    private Socket client;

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

    
    
}
