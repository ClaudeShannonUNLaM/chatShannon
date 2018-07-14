package bot.asistente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.cliente.userInterface.AgregarContacto;
import chat.cliente.userInterface.Login;
import chat.cliente.userInterface.NuevaSala;
import chat.cliente.userInterface.NuevoUsuario;
import chat.serverUtils.ServerResponse;
import hibernate.usuario.Usuario;

public class ClienteBot extends Thread {
	private Asistente bot;
	private String host;
    private int puerto;
    private String nombreUsuario;
    private ThreadLecturaBot threadLectura; //Thread que lee mensajes recividos desde el server.
    private ThreadEscrituraBot threadEscritura; //Thread que manda mensajes al server
	
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
	
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    public String getNombreUsuario() {
        return this.nombreUsuario;
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
		
		switch (funcionalidad) {
			case "login":

				break;
			case "mensajeRecivido":
				String mensaje = (String) response.getDatos().get("mensaje");
				Usuario usuarioEmi = (Usuario) response.getDatos().get("emisor");
				try {
					bot.escuchar(mensaje, usuarioEmi.getNombre());					
				}catch(Exception e) {
					
				}
				break;
		default:
			break;
		}
	}
}
