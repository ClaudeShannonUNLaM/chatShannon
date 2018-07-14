package bot.asistente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.Mensaje;
import chat.serverUtils.ServerRequest;
import chat.serverUtils.ServerResponse;
import hibernate.sala.Sala;
import hibernate.usuario.Usuario;

public class ClienteBot extends Thread {
	private Asistente bot;
	private String host;
    private int puerto;
    private Usuario usuario;
    private ThreadLecturaBot threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscrituraBot threadEscritura; //Thread que manda mensajes al server
    private Gson gson = new Gson();
    private HashMap<String,Object> datos;
	
	public ClienteBot(String host, int puerto){		
		this.host = host;
        this.puerto = puerto;
        this.bot = new Asistente();
	}
	
	public ClienteBot(String host, int puerto, String nombreBot){		
		this.host = host;
        this.puerto = puerto;
        this.bot = new Asistente(nombreBot);
	}
    
    public void run() {
        try {
            Socket socket = new Socket(host, puerto);
	        threadLectura = new ThreadLecturaBot(socket, this); 
	        threadEscritura = new ThreadEscrituraBot(socket, this);

	        threadLectura.start();
	        threadEscritura.start();

	        login();
	        
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        } 
    }

	public ThreadLecturaBot getThreadLectura() {
		return threadLectura;
	}

	public ThreadEscrituraBot getThreadEscritura() {
		return threadEscritura;
	}
	
	public void atender(ServerResponse response) {
		String funcionalidad = (String)response.getDatos().get("funcionalidad");
		datos = new HashMap<String,Object>();
		
		switch (funcionalidad) {
			case "login":
					if(!(boolean)response.getDatos().get("exito")) {
						login();
					}else {
						System.out.println("Login exitoso");

						LinkedTreeMap<String, Object> usu = (LinkedTreeMap<String, Object>) response.getDatos().get("usuario"); 
						usuario = new Usuario();
						
						double id = (double)usu.get("id");
						
						usuario.setId((int)id) ;
						usuario.setNombre((String)usu.get("nombre"));
						usuario.setPassword((String)usu.get("password"));
						usuario.setOnline((boolean)usu.get("online"));
					}
				break;
			case "mensajeRecivido":

				LinkedTreeMap<String, Object> men = (LinkedTreeMap<String, Object>) response.getDatos().get("mensaje");	
				
				Mensaje mensaje = translateMensaje(men); //Se traduce de GSON a mensaje
				Mensaje respuesta;
				try {
					respuesta = bot.escuchar(mensaje.getMensaje(), mensaje.getEmisor().getNombre());
				}catch(Exception e) {
					respuesta = new Mensaje("Error analizando su solicitud");
				}
				
				if(respuesta == null)
					return;

				respuesta.setEmisor(usuario);
				
				if(mensaje.getSala() != null) {
					respuesta.setSala(mensaje.getSala());
				}else {
					respuesta.setUsuarioDestinatario(mensaje.getEmisor());
				}

				datos.put("mensaje",respuesta);
				ServerRequest request = new ServerRequest(datos,FuncionalidadServerEnum.ENVIARMENSAJE);
				String requestServer = gson.toJson(request);
				
				threadEscritura.AddRequest(requestServer);
				break;
		default:
			break;
		}
	}
	
	private void login() {
		datos = new HashMap<String,Object>();
        datos.put("nombreUsuario", "iooi");
        datos.put("passUsuario", "91");
System.out.println("intentoLog");
        ServerRequest request = new ServerRequest(datos,FuncionalidadServerEnum.LOGIN);				
		String requestJson = gson.toJson(request);
		threadEscritura.AddRequest(requestJson);
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
		ClienteBot bot = new ClienteBot("localhost",10000);
		bot.run();
	}
}