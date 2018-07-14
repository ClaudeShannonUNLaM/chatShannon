package chat.serverChat;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import chat.serverUtils.Mensaje;
import chat.serverUtils.ServerRequest;
import chat.serverUtils.ServerResponse;
import hibernate.contacto.Contacto;
import hibernate.contacto.ContactoController;
import hibernate.sala.Sala;
import hibernate.sala.SalaController;
import hibernate.usuario.Usuario;
import hibernate.usuario.UsuarioController;
import hibernate.usuarioSala.UsuarioSala;
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
	
	@SuppressWarnings("resource")
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
	
	//Cierra el thread para un usuario que se desconecto
    void removeUsuario(UsuarioThread usu) {
        usuarioThreads.remove(usu);
    }
	
	public ServerResponse atenderRequest(ServerRequest request, UsuarioThread usuThread) {
		ServerResponse response = null;
		HashMap<String, Object> datos = new HashMap<String,Object>();
		boolean exito; 
		
		switch (request.getFuncionalidad()) { //LoggOff no se atiende, se deja pasar.
		
		case LOGIN:
			Usuario usuario = UsuarioController.usuarioYaCreado((String)request.getDatos().get("nombreUsuario"),(String)request.getDatos().get("passUsuario"),false);
			
			datos.put("usuario", usuario ); 
			datos.put("exito", usuario != null);
			datos.put("funcionalidad", "login");
			break;
		case CARGARDATOSINICIALES: //Cargo los datos que necesita el cliente al entrar por primera vez a la página principal.
			
			List<Sala> salasPublicas =  SalaController.BuscarSalas();
			List<Sala> salasPrivadas =  UsuarioSalaController.BuscarSalaUsuario((String)request.getDatos().get("nombreUsuario"));
			List<Usuario> contactos = new ArrayList<Usuario>();//ContactoController.buscarContactos((String)request.getDatos().get("nombreUsuario"));
			
			datos.put("salasPublicas", salasPublicas);
			datos.put("salasPrivadas", salasPrivadas);
			datos.put("contactos", contactos);
			datos.put("funcionalidad", "cargaInicial");			
			break;
		case NUEVOUSUARIO:
			exito = UsuarioController.crearNuevoUsuario((String)request.getDatos().get("nombreUsuario"),(String) request.getDatos().get("passUsuario"));
			datos.put("exito", exito);
			datos.put("funcionalidad", "nuevoUsuario");
			break;
			
		case NUEVASALA:			
			exito = SalaController.CrearSala((Sala)request.getDatos().get("nuevaSala"));
			datos.put("exito", exito);
			datos.put("funcionalidad", "nuevaSala");			
			break;
			
		case AGREGARUSUARIOSALA:
			exito = SalaController.CrearSala((Sala)request.getDatos().get("nuevaSala"));
			datos.put("exito", exito);
			datos.put("funcionalidad", "nuevaSala");
			break;
			
		case NUEVOCONTACTO:
			exito = ContactoController.agregarNuevoContacto((String)request.getDatos().get("usuarioIngresado"),(String)request.getDatos().get("nombreNuevoContacto"));
			datos.put("exito", exito);
			datos.put("funcionalidad", "nuevoContacto");
			break;	

		case ENVIARMENSAJE:
			Mensaje mensaje = (Mensaje)request.getDatos().get("mensaje");
			
			Usuario usuDest = mensaje.getUsuarioDestinatario();
			Sala salaDest = mensaje.getSala();
			ArrayList<Usuario> destinatarios = new ArrayList<Usuario>();

			if(usuDest != null) {
				destinatarios.add(usuDest);
				destinatarios.add(usuThread.getUsuario());
			}else {
				//I need dis so bad, so lautaro, cuando lo tengas descomentá esto.
				//destinatarios = UsuarioSala.getUsuariosPorSala(salaDest.getId());
			}			
			datos.put("mensaje",mensaje);
			
			ServerResponse responseMensaje = new ServerResponse(datos);
			responseMensaje.getDatos().put("funcionalidad","mensajeRecivido");		

			for(int i = 0; i < usuarioThreads.size(); i++) {
				for(int j = 0; j < destinatarios.size(); j++) {
					if(usuarioThreads.get(i).getUsuario().getId() == destinatarios.get(j).getId()) {
						usuarioThreads.get(i).enviarMensaje(responseMensaje);
						destinatarios.remove(j);
						break;
					}
				}
			}
			
			break;
		}	
		
		response = new ServerResponse(datos);
		return response;
	}
	
	public static void main(String[] args) {   	
		ServerChat server = new ServerChat();
		server.run();
	}
	

}
