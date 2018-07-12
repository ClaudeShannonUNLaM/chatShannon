package chat.cliente;

//import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente extends Thread{
	
	private String host;
    private int puerto;
    private String nombreUsuario;
    private Thread ThreadLectura; //Thread que lee mensajes recividos desde el server.
    private Thread ThreadEscritura; //Thread que manda mensajes al server
	
	public Cliente(String host, int puerto){		
		this.host = host;
        this.puerto = puerto;
	}
	
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void run() {
        try {
            Socket socket = new Socket(host, puerto);
	        ThreadLectura = new ThreadLectura(socket, this); 
	        ThreadEscritura =  new ThreadEscritura(socket, this);
	        
	        ThreadLectura.start();
	        ThreadEscritura.start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        } 
    }
 
 
    /*public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	System.out.println("Introduzca la dirección del servidor:");
    	String host = console.nextLine();
    	System.out.println("Introduzca el puerto:");
    	int puerto = console.nextInt();
    	//console.close();
        Cliente cliente = new Cliente(host, puerto);
        //Cliente cliente = new Cliente("192.168.0.101", 10000);
        cliente.run();
        //console.close();
    }
    */

}
