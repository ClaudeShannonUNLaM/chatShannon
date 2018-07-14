package chat.serverChat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import com.google.gson.Gson;

import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.ServerRequest;
import chat.serverUtils.ServerResponse;
import hibernate.usuario.Usuario;

public class UsuarioThread extends Thread{
	private Socket socket;
    private ServerChat server;
    private PrintWriter writer;
    private Usuario usuario;
 
    public UsuarioThread(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }
 
    public void run() {
        try {
        	
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); //Obtiene lo que el cliente envia
 
            
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            writer = new PrintWriter(output, true); //Devuelve la respuesta al cliente           
            
            
            String mensajeCliente;               
            Gson gson = new Gson();
        	ServerRequest request;
        	
            do {
            	mensajeCliente = reader.readLine(); 
            	request = gson.fromJson(mensajeCliente, ServerRequest.class);            	
            	ServerResponse response = server.atenderRequest(request,this);
            	enviarMensaje(response);
                
            } while (request.getFuncionalidad() != FuncionalidadServerEnum.LOGOFF );
 
            //Remuevo el usuario y su socket.
            server.removeUsuario(this);
            socket.close();           
 
        } catch (IOException ex) {
            System.out.println("Error UsuarioThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }    
 
    
    void enviarMensaje(ServerResponse response) {
    	Gson gson = new Gson();
    	String message = gson.toJson(response);
        writer.println(message);
    }
    
}
