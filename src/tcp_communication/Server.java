/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcp_communication;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessio Betti
 * @see pp. 120-130 libro di testo TPSIT terzo volume
 */
public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader stringaClient;
    DataOutputStream dos;
    
    
    public Socket attendi(){
         
        try {
            System.out.println("Server in esecuzione e in ascolto\n");
            System.out.println(" ___________________________________________\n");
            System.out.println("      Server in esecuzione e in ascolto\n");
            System.out.println(" ___________________________________________\n");
            
            server = new ServerSocket(1789);
            client = server.accept();
            server.close();
            
            stringaClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            dos = new DataOutputStream(client.getOutputStream());
          
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return client; 
    }
    
    
    public void comunica(){
        
        try {
            stringaRicevuta = stringaClient.readLine();
            
            System.out.println("Frase ricevuta!\n");
            System.out.println("Frase: " + stringaRicevuta + "\n");
            
            stringaModificata = stringaRicevuta.toUpperCase();
            dos.writeBytes(stringaModificata + "\n");
            
            
            System.out.println("Fase di elaborazione terminata, controllare il risultato\n");
            client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.attendi();
        server.comunica();
    }
    
}
 