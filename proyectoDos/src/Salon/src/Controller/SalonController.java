package Salon.src.Controller;

import Patterns.Observable;
import Patterns.*;

import Salon.src.Model.SalonModel;
import Salon.src.View.GuiSalon;
import SharedClasses.MensajeNotif;
import SharedClasses.MensajeOrden;
import SharedClasses.OrdenModel;

import java.util.*;
import java.net.*;
import java.io.*;

import Constants.*;

public class SalonController extends Observable implements IObserver {
    private SalonModel salonModel;

    public SalonController() {
        System.out.println("--------------------SALON--------------------");
        salonModel = new SalonModel();
        iniciarMesas();
        Thread abrirServerCocina = new Thread(() -> abrirConexionCocina());
        abrirServerCocina.start();
        Thread conectarServer = new Thread(() -> conectar());
        conectarServer.start();
        Thread abrirServerSimulacion = new Thread(() -> abrirConexionSimulacion());
        abrirServerSimulacion.start();
    }

    public void conectar() {
        // este es para conectar con cocina
        try {
            Socket salonClient = new Socket("127.0.0.1", Constants.COCINA_PORT);
            salonModel.setClientConnect(salonClient);
            mostrarInterfaz();
            while (true) {
                if (salonModel.getOutput() != null) {
                    ObjectOutputStream output = salonModel.getOutput();
                    output.flush();
                    salonModel.setOutput(null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void abrirConexionCocina() {
        // este es para que cocina se conecte a salon
        try {
            System.out.println("Se abrio SALON_PORT2");
            ServerSocket salonServer = new ServerSocket(Constants.SALON_PORT2);
            System.out.println("Salon esta esperando conexion de cocina");
            Socket client = salonServer.accept();
            salonModel.setClientAcceptCocina(client);
            salonModel.setSalonPort2(salonServer);
            while (true) {
                if (client.getInputStream().available() > 0) {
                    ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                    MensajeNotif mensaje = (MensajeNotif) input.readObject();
                    System.out.println("Salon recibió la notificación de facturar la mesa: " + mensaje.getMensaje());
                    notifyObservers(mensaje.getMensaje(), false);
                    salonModel.setInputCocina(null);
                    client = salonServer.accept();
                    salonModel.setClientAcceptCocina(client);
                    salonModel.setSalonPort2(salonServer);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void iniciarMesas() {
        ArrayList<Boolean> mesas = salonModel.getMesas();
        for (int index = 0; index < Constants.CANT_MESAS; ++index) {
            mesas.add(false);
        }

    }

    private void abrirConexionSimulacion() {
        // este es para que simulacion se conecte a salon
        try {
            System.out.println("Se abrio SALON_PORT");
            ServerSocket salonServer = new ServerSocket(Constants.SALON_PORT);
            System.out.println("Salon esta esperando conexion de simulacion");
            Socket client = salonServer.accept();
            salonModel.setClientAcceptSimulacion(client);
            salonModel.setSalonPort(salonServer);
            while (true) {
                if (client.getInputStream().available() > 0) {
                    System.out.println("Salon recibió la orden de simulacion");
                    ObjectInputStream input = new ObjectInputStream(client.getInputStream());
                    MensajeOrden mensaje = (MensajeOrden) input.readObject();
                    mensaje.mostrarOrden();
                    mensaje.getMensajeOrden().setNumMesa(asignarMesa());
                    ingresarOrden(mensaje.getMensajeOrden());
                    notifyObservers(mensaje.getMensajeOrden().getNumMesa(), mensaje.getMensajeOrden().getPrecio());
                    salonModel.setInputSimulacion(null);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void libreraMesa(int mesa) {
        salonModel.getMesas().set(mesa, false);

    }

    private int asignarMesa() {
        int mesa = 0;
        Random rand = salonModel.getRand();
        int num = 0 + rand.nextInt(Constants.CANT_MESAS);
        while(true){
            if (!salonModel.getMesas().get(num)) {
                mesa = num;
                salonModel.getMesas().set(num, true);
                break;
            }
        }
        return mesa;
    }

    public void ingresarOrden(OrdenModel pOrden) {
        // este metodo se encarga de escribir el output para mandarlo a cocina
        try {
            ObjectOutputStream output = new ObjectOutputStream(salonModel.getClientConnect().getOutputStream());
            MensajeOrden mensaje = new MensajeOrden();
            mensaje.setMensajeOrden(pOrden);
            output.writeObject(mensaje);
            salonModel.setOutput(output);
            salonModel.getMesas().set(pOrden.getNumMesa(), true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Observable pObservable, Object args, Object flag) {
        // si es true entonces es para liberar la mesa
        if((Boolean) flag){
            libreraMesa((int) args);
        }else{
            // sino para ingresar ordenes
            ingresarOrden((OrdenModel) args);
        }        
    }

    private void mostrarInterfaz() {
        GuiSalon guiSalon = salonModel.getGuiSalon();
        guiSalon = new GuiSalon();
        guiSalon.addObserver(this);
        addObserver(guiSalon);

    }

}