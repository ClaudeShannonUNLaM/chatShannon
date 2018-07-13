package chat.cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javax.swing.JFrame;

import com.google.gson.Gson;

import chat.clienteUtils.ResponseHandler;
import chat.serverUtils.ServerResponse;

public class ThreadLectura extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private Cliente cliente;
    private ServerResponse responseServer;    
    private JFrame pantallaActual; //El thread de lectura se encarga de recibir las respuestas
    							   //y llamar a los metodos con la respuesta del servidor
    
    public ServerResponse getResponseServer() {
		return responseServer;
	}

	public ThreadLectura(Socket socket, Cliente cliente) {
        this.socket = socket;
        this.cliente = cliente;
 
        try {
            DataInputStream input = new DataInputStream(this.socket.getInputStream());
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error leyendo input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
        while (true) {
            try {                            	
            	String response = reader.readLine();
            	Gson gson = new Gson();
            	responseServer = gson.fromJson(response, ServerResponse.class);
            	atenderRequest(responseServer);	
            }catch (IOException ex) {
            	if(!ex.getMessage().contains("Socket closed")) {
            		System.out.println("Error leyendo el servidor: " + ex.getMessage());
            		ex.printStackTrace();
            	}
                break;
            }
        }
    }
    
    private void atenderRequest(ServerResponse response) {
    	ResponseHandler handler;
    	
    	//switch(response.getDatos().get(0))
    }
}
