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
    private ThreadLectura threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscritura threadEscritura; //Thread que manda mensajes al server
	
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
	        threadLectura = new ThreadLectura(socket, this); 
	        threadEscritura = new ThreadEscritura(socket, this);

	        threadLectura.start();
	        threadEscritura.start(); 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        } 
    }

	public ThreadLectura getThreadLectura() {
		return threadLectura;
	}

	public ThreadEscritura getThreadEscritura() {
		return threadEscritura;
	}
}
