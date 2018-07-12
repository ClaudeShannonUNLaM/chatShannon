package chat.serverChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import chat.serverUtils.ServerRequest;
import chat.serverUtils.ServerResponse;
import hibernate.contacto.Contacto;
import hibernate.contacto.ContactoController;
import hibernate.sala.Sala;
import hibernate.sala.SalaController;
import hibernate.usuario.UsuarioController;
import hibernate.usuarioSala.UsuarioSalaController;

public class ServerChat{

	int puerto;
	ArrayList<UsuarioThread> usuarioThreads = new ArrayList<UsuarioThread>();	
	
	ServerChat(){
		puerto = 10000;
	}
	
	ServerChat(int puerto){
		this.puerto = puerto;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(puerto);
			while(true) {				
				Socket newSocket = serverSocket.accept(); 
				UsuarioThread newUsuario = new UsuarioThread(newSocket, this); //Una vez creado el socket, creo un nuevo thread de usuario
				usuarioThreads.add(newUsuario); // Agrego el thread a la lista de threads de usuarios
				newUsuario.start(); // Comienzo ejecución del thread que atienda las necesidades de ese usuario.				
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Envia el mensaje a todos los usuarios conectados
	void broadcast(String mensaje, UsuarioThread excluirUsuario) { 
        for (UsuarioThread usu : usuarioThreads) {
            if (usu != excluirUsuario) { //No se le envia el mensaje al usuario que lo inicio.
                usu.enviarMensaje(mensaje);
            }
        }
    }
	
	//Cierra el thread para un usuario que se desconecto
    void removeUsuario(UsuarioThread usu) {
        usuarioThreads.remove(usu);
    }
    
	public boolean loguear(String datos) {
		return UsuarioController.usuarioYaCreado(datos,datos,false);	
	}
	
	
	public ServerResponse atenderRequest(ServerRequest request) {
		ServerResponse response;
		
		switch (request.getFuncionalidad()) {
		
		case CARGARDATOSINICIALES: //Cargo los datos que necesita el cliente al entrar por primera vez a la página principal.
			
			List<Sala> salasPublicas =  SalaController.BuscarSalas();
			List<Sala> salasPrivadas =  UsuarioSalaController.BuscarSalaUsuario(request.getDatos().get("nombreUsuario"));
			List<Contacto> contactos = ContactoController.buscarContactos(request.getDatos().get("nombreUsuario"));
			HashMap<String, Object> datos = new HashMap<String,Object>();
			datos.put("salasPublicas", salasPublicas);
			datos.put("salasPrivadas", salasPrivadas);
			datos.put("contactos", contactos);
			response = new ServerResponse(datos);
			break;
			
			
		case OBTENERCONTACTOS:
			
			break;
			
		case OBTENERSALASPRIVADAS:
			
			break;
			
		case OBTENERSALASPUBLICA:
			
			break;
			
		case LLAMARBOT:
			
			break;

		default:
			break;
		}
		return response;
	}
	
	public static void main(String[] args) {   	
		ServerChat server = new ServerChat();
		server.run();
	}
	

}
