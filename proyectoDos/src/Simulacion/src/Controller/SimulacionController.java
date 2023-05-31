package Simulacion.src.Controller;

import Salon.src.Model.SalonModel;
import Cocina.src.Model.Hamburguesa;

import java.util.*;
import java.net.*;
import java.io.*;

import Constants.*;

public class SimulacionController extends SalonModel{
    private SalonModel salonModel;
    private Random rand;
    private Hamburguesa hamburguesa;

    String str;
    Socket client;
    ObjectOutputStream output;
    
    public SimulacionController(){
        salonModel = new SalonModel();
        Thread conectarServer = new Thread(() -> conectar());
        conectarServer.start();
    }

    public void crearHamburguesaRandom(){
        int hamburguesaRand = 0 + rand.nextInt(5);
        String nombre = "";
        ArrayList<String> ingredientes = new ArrayList<String>();
        switch(hamburguesaRand){
            case 0:
                nombre = "Hamburguesa doble torta";
                ingredientes.add("Pan");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
            case 1:
                nombre = "Hamburguesa con bacon";
                ingredientes.add("Pan");
                ingredientes.add("Salsa BBQ");
                ingredientes.add("Bacon");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
            case 2:
                nombre = "Hamburguesa con queso";
                ingredientes.add("Pan");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
            case 3:
                nombre = "Hamburguesa Especial";
                ingredientes.add("Pan");
                ingredientes.add("Tomate");
                ingredientes.add("Lechuga");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Bacon");
                ingredientes.add("Aguacate");
                ingredientes.add("Cebolla");
                ingredientes.add("Torta");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
            case 4:
                nombre = "Hamburguesa Clasica";
                ingredientes.add("Pan");
                ingredientes.add("Tomate");
                ingredientes.add("Lechuga");
                ingredientes.add("Pepinillos");
                ingredientes.add("Queso");
                ingredientes.add("Cebolla");
                ingredientes.add("Salsa de tomate");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
            case 5:
                nombre = "Hamburguesa Base";
                ingredientes.add("Pan");
                ingredientes.add("Torta");
                ingredientes.add("Pan");
                hamburguesa.setNombre(nombre);
                hamburguesa.setHamburguesa(ingredientes);
                break;
        }
    }

    public void conectar(){
        // este es para conectar con salon
        str = "se conecto";
        try{
            client = new Socket("127.0.0.1", Constants.SALON_PORT2);
            salonModel.setClientConnect(client);
            output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(str);
            output.flush();
            output.close();
            client.close();
           // mostrarInterfaz();
           // while(true){
            //    if(salonModel.getOutput() != null){    
             //       ObjectOutputStream output = salonModel.getOutput();
              //      output.flush();
               //     salonModel.setOutput(null);
                //}
           // }
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
