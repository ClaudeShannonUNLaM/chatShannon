package bot.asistente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.google.gson.Gson;

import chat.serverUtils.FuncionalidadServerEnum;
import chat.serverUtils.Mensaje;
import chat.serverUtils.ServerRequest;
import chat.serverUtils.ServerResponse;
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

				break;
			case "mensajeRecivido":
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
				break;
		default:
			break;
		}
	}
}
