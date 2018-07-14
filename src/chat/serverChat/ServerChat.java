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
import com.google.gson.internal.LinkedTreeMap;

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
			if(usuario != null) {
				usuThread.setUsuario(usuario);
			}
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

			LinkedTreeMap<String, Object> men = (LinkedTreeMap<String, Object>) request.getDatos().get("mensaje");	
			
			Mensaje mensaje = translateMensaje(men); //Se traduce de GSON a mensaje
			
			Usuario usuDest = mensaje.getUsuarioDestinatario();
			Sala salaDest = mensaje.getSala();
			List<Usuario> destinatarios = new ArrayList<Usuario>();

			if(usuDest != null) {
				destinatarios.add(usuDest);
				destinatarios.add(usuThread.getUsuario());
			}else {
				destinatarios = UsuarioSalaController.getUsuariosPorSala(salaDest.getId());
			}			
			datos.put("mensaje",mensaje);
			
			ServerResponse responseMensaje = new ServerResponse(datos);
			responseMensaje.getDatos().put("funcionalidad","mensajeRecivido");		

			for(int i = 0; i < usuarioThreads.size(); i++) {
				for(int j = 0; j < destinatarios.size(); j++) {
					if(usuarioThreads.get(i).getUsuario().getId() == destinatarios.get(j).getId()) {
						usuarioThreads.get(i).enviarMensaje(responseMensaje);
						//destinatarios.remove(j);
						break;
					}
				}
			}
			
			//lo seteo en null así es ignorado por el cliente
			responseMensaje.getDatos().put("funcionalidad","");	
			
			break;
		}	
		
		response = new ServerResponse(datos);
		return response;
	}
	
	private Mensaje translateMensaje(LinkedTreeMap<String, Object> men) {
		
		Mensaje mensaje = new Mensaje();		
		
		//Creo el usuario emisor
		LinkedTreeMap<String, Object> usu = (LinkedTreeMap<String, Object>) men.get("emisor"); 
		Usuario usuarioAux = new Usuario();
		
		double id = (double)usu.get("id");
		
		usuarioAux.setId((int)id) ;
		usuarioAux.setNombre((String)usu.get("nombre"));
		usuarioAux.setPassword((String)usu.get("password"));
		usuarioAux.setOnline((boolean)usu.get("online"));

		//Creo la sala desde la que se envio (al menos que provenga de una conversación privada)
		LinkedTreeMap<String, Object> sa = (LinkedTreeMap<String, Object>) men.get("sala");
		Sala nuevaSala;
		
		if(sa != null) {
			id = (double)sa.get("id");
			nuevaSala = new Sala((int)id,(String)sa.get("nombre"),(boolean)sa.get("privada"));	
		}
		else
			nuevaSala = null;
		
		
		//Creo el usuario destinatario			
		
		usu = (LinkedTreeMap<String, Object>) men.get("usuarioDestinatario");
		Usuario destinatario; 
		
		if(usu != null) {
			destinatario = new Usuario();
			id = (double)usu.get("id");
			
			destinatario.setId((int)id) ;
			destinatario.setNombre((String)usu.get("nombre"));
			destinatario.setPassword((String)usu.get("password"));
			destinatario.setOnline((boolean)usu.get("online"));
		}			
		else
			destinatario = null;
		
		mensaje.setEmisor(usuarioAux);
		mensaje.setMensaje((String)men.get("mensaje"));
		mensaje.setSala(nuevaSala);
		mensaje.setUsuarioDestinatario(destinatario);
		
		return mensaje;
	}
	
	public static void main(String[] args) {   	
		ServerChat server = new ServerChat();
		server.run();
	}
	

}

