package chat.cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreadEscritura extends Thread {
 	private PrintWriter writer;
    private Socket socket;
    private Cliente cliente;    
    private ArrayList<String> listaRequest;
    
    public ThreadEscritura(Socket socket, Cliente cliente) {
        this.socket = socket;
        this.cliente = cliente;
        listaRequest = new ArrayList<String>();
        
        try {
            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error obteniendo output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {       	        
    	while(true) {			
			if(!listaRequest.isEmpty()) {
				writer.println(listaRequest.get(0));
				listaRequest.remove(0);
			}
			else {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
				
    	}
    }	

	public void AddRequest(String mensaje) {
		listaRequest.add(mensaje);
	}
}
