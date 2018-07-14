package chat.cliente;

//import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import hibernate.usuario.Usuario;

public class Cliente extends Thread{
	
	private String host;
    private int puerto;
    private Usuario Usuario;
    private ThreadLectura threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscritura threadEscritura; //Thread que manda mensajes al server
	
	public Cliente(String host, int puerto){		
		this.host = host;
        this.puerto = puerto;
	}
	
    public void setNombreUsuario(Usuario usuario) {
        this.Usuario = usuario;
    }
 
    public Usuario getUsuario() {
        return this.Usuario;
    }
    
    public void run() {
        try {
            Socket socket = new Socket(host, puerto);
	        threadLectura = new ThreadLectura(socket, this); 
	        threadEscritura =  new ThreadEscritura(socket, this);
	        
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
