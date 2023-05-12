package Cocina.src.Model;

import java.util.*;

import Sockets.Server;

public class CocinaModel{
    ArrayList<Orden> ordenesPendientes;
    private Server salonServer;

    public CocinaModel(){
        ordenesPendientes = new ArrayList<>();
    }

    public ArrayList<Orden> getOrdenesPendientes() {
        return ordenesPendientes;
    }

    public void setOrdenesPendientes(ArrayList<Orden> ordenesPendientes) {
        this.ordenesPendientes = ordenesPendientes;
    }

    public Server getSalonServer() {
        return salonServer;
    }

    public void setSalonServer(Server salonServer) {
        this.salonServer = salonServer;
    } 

    
    
}
