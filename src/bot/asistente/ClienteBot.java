package bot.asistente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
<<<<<<< HEAD

import chat.cliente.userInterface.AgregarContacto;
import chat.cliente.userInterface.Login;
import chat.cliente.userInterface.NuevaSala;
import chat.cliente.userInterface.NuevoUsuario;
=======
import java.util.HashMap;

import com.google.gson.Gson;

import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.Mensaje;
import chat.serverUtils.ServerRequest;
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
import chat.serverUtils.ServerResponse;
import hibernate.usuario.Usuario;

public class ClienteBot extends Thread {
	private Asistente bot;
	private String host;
    private int puerto;
<<<<<<< HEAD
    private String nombreUsuario;
    private ThreadLecturaBot threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscrituraBot threadEscritura; //Thread que manda mensajes al server
=======
    private Usuario usuario;
    private ThreadLecturaBot threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscrituraBot threadEscritura; //Thread que manda mensajes al server
    private Gson gson = new Gson();
    private HashMap<String,Object> datos;
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
	
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
<<<<<<< HEAD
	
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
=======
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
    
    public void run() {
        try {
            Socket socket = new Socket(host, puerto);
	        threadLectura = new ThreadLecturaBot(socket, this); 
	        threadEscritura = new ThreadEscrituraBot(socket, this);

	        threadLectura.start();
	        threadEscritura.start();
	        
<<<<<<< HEAD
=======
	        	        
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
	        
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
<<<<<<< HEAD
		String funcionalidad = (String)response.getDatos().get("funcionalidad");	
=======
		String funcionalidad = (String)response.getDatos().get("funcionalidad");
		datos = new HashMap<String,Object>();
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
		
		switch (funcionalidad) {
			case "login":

				break;
			case "mensajeRecivido":
<<<<<<< HEAD
				String mensaje = (String) response.getDatos().get("mensaje");
				Usuario usuarioEmi = (Usuario) response.getDatos().get("emisor");
				try {
					bot.escuchar(mensaje, usuarioEmi.getNombre());					
				}catch(Exception e) {
					
				}
=======
				Mensaje mensaje = (Mensaje) response.getDatos().get("mensaje");
				Mensaje respuesta;
				try {
					respuesta = bot.escuchar(mensaje.getMensaje(), mensaje.getEmisor().getNombre());
				}catch(Exception e) {
					respuesta = new Mensaje("Error analizando su solicitud");
				}

				mensaje.setEmisor(usuario);
				
				if(mensaje.getUsuarioDestinatario() != null) {
					respuesta.setUsuarioDestinatario(mensaje.getEmisor());
				}else {
					respuesta.setSala(mensaje.getSala());
				}
				
				datos.put("mensaje",respuesta);
				ServerRequest request = new ServerRequest(datos,FuncionalidadServerEnum.ENVIARMENSAJE);
				String requestServer = gson.toJson(request);
				
				threadEscritura.AddRequest(requestServer);
>>>>>>> 00d0f4f0d86567a8463bd824a77903f09cf306b0
				break;
		default:
			break;
		}
	}
}
