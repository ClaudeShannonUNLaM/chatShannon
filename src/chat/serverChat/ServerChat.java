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
    
	public boolean loguear(ServerRequest datos) {
		return UsuarioController.usuarioYaCreado((String)datos.getDatos().get("nombreUsuario"),(String)datos.getDatos().get("passUsuario"),false);	
	}
	
	
	public ServerResponse atenderRequest(ServerRequest request) {
		ServerResponse response = null;
		HashMap<String, Object> datos = new HashMap<String,Object>();
		boolean creadoConExito; 
		
		switch (request.getFuncionalidad()) {
		
		case CARGARDATOSINICIALES: //Cargo los datos que necesita el cliente al entrar por primera vez a la página principal.
			
			List<Sala> salasPublicas =  SalaController.BuscarSalas();
			List<Sala> salasPrivadas =  UsuarioSalaController.BuscarSalaUsuario((String)request.getDatos().get("nombreUsuario"));
			List<Contacto> contactos = ContactoController.buscarContactos((String)request.getDatos().get("nombreUsuario"));
			
			datos.put("salasPublicas", salasPublicas);
			datos.put("salasPrivadas", salasPrivadas);
			datos.put("contactos", contactos);
			response = new ServerResponse(datos);
			
		case NUEVOUSUARIO:
			creadoConExito = UsuarioController.crearNuevoUsuario((String)request.getDatos().get("nombreUsuario"),(String) request.getDatos().get("passUsuario"));
			datos.put("exito", creadoConExito);
			response = new ServerResponse(datos);
			
		case NUEVASALA:			
			creadoConExito = SalaController.CrearSala((Sala)request.getDatos().get("nuevaSala"));
			datos.put("exito", creadoConExito);
			response = new ServerResponse(datos);
			
		case NUEVOCONTACTO:
			creadoConExito = ContactoController.agregarNuevoContacto((String)request.getDatos().get("usuarioIngresado"),(String)request.getDatos().get("nombreNuevoContacto"));
		}
		
		return response;
	}
	
	public static void main(String[] args) {   	
		ServerChat server = new ServerChat();
		server.run();
	}
	

}
