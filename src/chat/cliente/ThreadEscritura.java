package chat.cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEscritura extends Thread {
	 	private PrintWriter writer;
	    private Socket socket;
	    private Cliente cliente;
	 
	    public ThreadEscritura(Socket socket, Cliente cliente) {
	        this.socket = socket;
	        this.cliente = cliente;
	 
	        try {
	            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
	            writer = new PrintWriter(output, true);
	        } catch (IOException ex) {
	            System.out.println("Error obteniendo output stream: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	 
	    public void run() {
	        String mensaje;
	 
	        do {	            
	        	mensaje = "";
	            writer.println(mensaje);
	 
	        } while (!mensaje.equals("desconectar"));
	 
	        try {	        	
	            socket.close();
	        } catch (IOException ex) {
	 
	            System.out.println("Error escribiendo en el servidor: " + ex.getMessage());
	        }
	    }
}
