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
	
	Cliente(String host, int puerto){		
		this.host = host;
        this.puerto = puerto;
	}
	
    void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void run() {
        try {
            Socket socket = new Socket(host, puerto);
 
            System.out.println("Conectado al servidor de chat");
 
            new ThreadLectura(socket, this).start();
            new ThreadEscritura(socket, this).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
 
    public static void main(String[] args) {
        /*if (args.length < 2) return;
 
        String host = args[0];
        int puerto = Integer.parseInt(args[1]);*/
    	
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

}
