package chat.serverChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerChat{

	int puerto;
	ArrayList<UsuarioThread> usuarioThreads = new ArrayList<UsuarioThread>();
	private ArrayList<String> usuarioNombres = new ArrayList<String>();
	
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
				System.out.println("Esperando");
				Socket newSocket = serverSocket.accept(); //espero a aceptar un nuevo socket
				UsuarioThread newUsuario = new UsuarioThread(newSocket, this); //una vez creado el socket, creo un nuevo thread de usuario
				usuarioThreads.add(newUsuario); // agrego el thread a la lista de threads de usuarios
				newUsuario.start(); // comienzo ejecución del thread
				System.out.println("Nuevo usuario conectado");
			}
			//serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * envia un mensaje a todos los usuarios
	 */
	void broadcast(String mensaje, UsuarioThread excluirUsuario) {
        for (UsuarioThread usu : usuarioThreads) {
            if (usu != excluirUsuario) {
                usu.enviarMensaje(mensaje);
            }
        }
    }
 
    void addUsuarioNombre(String userName) {
    	usuarioNombres.add(userName);
    }
    
    /*
     *elimina al usuario de la lista y cierra el thread 
     */
    void removeUsuario(String nombreUsuario, UsuarioThread usu) {
        boolean removed = usuarioNombres.remove(nombreUsuario);
        if (removed) {
            usuarioThreads.remove(usu);
            System.out.println("El usuario " + nombreUsuario + " se desconectó");
        }
    }
 
    ArrayList<String> getUsuarioNombres() {
        return this.usuarioNombres;
    }
 
    boolean tieneUsuarios() {
        return !this.usuarioNombres.isEmpty();
    }
	
	public static void main(String[] args) {
		/*if (args.length == 1) {
			ServerChat server = new ServerChat(Integer.parseInt(args[0]));
        }*/
		Scanner console = new Scanner(System.in);
    	System.out.println("Introduzca el puerto:");
    	int puerto = console.nextInt();
		ServerChat server = new ServerChat(puerto);
		server.run();
	}
}
