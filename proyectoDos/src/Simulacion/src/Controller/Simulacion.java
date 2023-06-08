package Simulacion.src.Controller;

import Salon.src.Model.SalonModel;
import SharedClasses.Hamburguesa;
import SharedClasses.MensajeOrden;
import SharedClasses.OrdenModel;

import java.util.*;
import java.net.*;
import java.io.*;

import Constants.*;

public class Simulacion {
    private Random rand;
    private Hamburguesa hamburguesa;
    Socket client;
    ObjectOutputStream output;
    
    public Simulacion(){
        System.out.println("--------------------SIMULACION--------------------");
        rand = new Random(134564);
        hamburguesa = new Hamburguesa();
        Thread conectarServer = new Thread(() -> conectar());
        conectarServer.start();
    }

    public void crearHamburguesaRandom(){
        int hamburguesaRand = 0 + rand.nextInt(6);
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
        try{
            client = new Socket("127.0.0.1", Constants.SALON_PORT);
            while(true){
                crearHamburguesaRandom();
                // va a mandar ordenes y ya despues salon se encarga de darle la mesa
                OrdenModel orden = new OrdenModel();
                orden.setHamburguesa(hamburguesa);
                output = new ObjectOutputStream(client.getOutputStream());
                MensajeOrden mensaje = new MensajeOrden();
                System.out.println("Se creo la hamburguesa: " + hamburguesa.getNombre());
                mensaje.setMensajeOrden(orden);
                output.writeObject(mensaje);
                output.flush();
                int time = (2 + rand.nextInt(10)) * 1000;
                Thread.sleep(time);


            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
