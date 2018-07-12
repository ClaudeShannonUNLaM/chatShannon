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
	    private String request;
	    
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
	    	
	    	while(true) {	    	
	    		try {
					wait();
					writer.println(request);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}	            
	    		
	    	}
	 
	    }

		public String getRequest() {
			return request;
		}

		public void setRequest(String mensaje) {
			this.request = mensaje;
		}
}
