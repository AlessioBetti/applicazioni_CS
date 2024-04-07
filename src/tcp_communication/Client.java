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
public class Client {
    
    String nomeServer = "localhost";
    int portaServer = 1789;
    Socket socket;
    BufferedReader keyboard;
    String stringaUser;
    String stringaServer;
    DataOutputStream dos;
    BufferedReader stringFromServer;
    
    
    public Socket connetti(){
        
        System.out.println(" ______________________________\n");
        System.out.println("      Client in esecuzione\n");
        System.out.println(" ______________________________\n");

        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, portaServer);
            
            dos = new DataOutputStream(socket.getOutputStream());
            stringFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
        } catch(UnknownHostException e){
            System.err.println("host sconosciuto!");
        }
        catch (IOException e){
            System.err.println("Server non pronto!!");
            System.err.println("Riprova!!\n");
            System.exit(1);
        }
        
        return socket;
    }
    
    
    public void comunica(){
        try {
            System.out.println("Digita la stringa da trasformare in maiuscolo che vuoi comunicare al server:\n");
            stringaUser = keyboard.readLine();
            
            System.out.println("Invio in corso... Attendere prego...\n");
            dos.writeBytes(stringaUser + "\n");
            System.out.println("\n");
            
            stringaServer = stringFromServer.readLine();
            System.out.println("Frase modificata dal server:\n" + stringaServer + "\n");
            
            System.out.println("Comunicazione completata, connessione in chiusura\n");
            socket.close();
            
  
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.err.println("Errore durante la comunicazione!");
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        client.connetti();
        client.comunica();
        
    }
    
}
