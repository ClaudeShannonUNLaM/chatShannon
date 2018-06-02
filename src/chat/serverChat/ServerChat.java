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
				Socket newSocket = serverSocket.accept();
				UsuarioThread newUsuario = new UsuarioThread(newSocket, this);
				usuarioThreads.add(newUsuario);
				newUsuario.start();
				System.out.println("Nuevo usuario conectado");
			}
			//serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
 
    void removeUsuario(String nombreUsuario, UsuarioThread usu) {
        boolean removed = usuarioNombres.remove(nombreUsuario);
        if (removed) {
            usuarioThreads.remove(usu);
            System.out.println("El usuario " + nombreUsuario + " se desconect�");
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
