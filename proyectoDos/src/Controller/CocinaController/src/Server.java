package Controller.CocinaController.src;
import java.net.*;
import java.io.*;

public class Server {
    String str;
    
    Socket client;
    ServerSocket server;
    ObjectInputStream input;

    public Server(){
        abrirConexion();

    }

    public void abrirConexion(){
        try{
            server = new ServerSocket(2345);
            client = server.accept();
            
            input = new ObjectInputStream(client.getInputStream());
            str = (String)input.readObject();
            System.out.println("se conecto");
            input.close();
            client.close();
            server.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
