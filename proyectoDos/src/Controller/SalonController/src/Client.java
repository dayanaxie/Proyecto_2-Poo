package Controller.SalonController.src;
import java.net.*;
import java.util.logging.SocketHandler;
import java.io.*;

// el salon tiene que ser client de cocina
// cocina tiene que ser server de salon
// para asi poder mandar las ordenes generadas


public class Client {
    Socket cliente;
    String str;
    ObjectOutputStream output;
    
    public Client(){
        conectar();
    }

    public void conectar(){
        str = "HOLA MUNDO";
        try{
            cliente = new Socket("127.0.0.1", 9876);
            output = new ObjectOutputStream(cliente.getOutputStream());
            output.writeObject(str);
            // lo envia
            output.flush(); 
            output.close();
            cliente.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
