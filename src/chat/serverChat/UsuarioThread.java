package chat.serverChat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class UsuarioThread extends Thread{
	private Socket socket;
    private ServerChat server;
    private PrintWriter writer;
 
    public UsuarioThread(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }
 
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            writer = new PrintWriter(output, true);
 
            usuariosConectados();
 
            String nombreUsuario = reader.readLine();
            server.addUsuarioNombre(nombreUsuario);
 
            String mensajeServer = "Nuevo usuario conectado: " + nombreUsuario;
            server.broadcast(mensajeServer, this);
 
            String mensajeCliente;
 
            do {
            	mensajeCliente = reader.readLine();
            	mensajeServer = "[" + nombreUsuario + "]: " + mensajeCliente;
                server.broadcast(mensajeServer, this);
 
            } while (!mensajeCliente.equals("desconectar"));
 
            server.removeUsuario(nombreUsuario, this);
            socket.close();
 
            mensajeServer = nombreUsuario + " se desconectó.";
            server.broadcast(mensajeServer, this);
 
        } catch (IOException ex) {
            System.out.println("Error UsuarioThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    void usuariosConectados() {
        if (server.tieneUsuarios()) {
            writer.println("Usuarios conectados: " + server.getUsuarioNombres());
        } else {
            writer.println("No hay usuarios conectados");
        }
    }
 
    void enviarMensaje(String message) {
        writer.println(message);
    }
}
