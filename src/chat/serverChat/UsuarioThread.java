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
        	/*
        	 * seteo rel input que luego se lo paso al reader que se encarga de leer lo que envía por el socket
        	 * ver de implementar JSON con librería GSON o Jackson. Sino hay que manejarlo con objectos D:
        	 */
            DataInputStream input = new DataInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            /*
             * steo un canar de output para que el writer pueda escribir. 
             * Esto no debería de utilizarse, ya que solo tiene que devolver datos el sv.
             * acá solo se usa para indicar los usuarios conectados. se puede sacar en la implementacion
             */
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            writer = new PrintWriter(output, true);
 
            usuariosConectados();
 
            /*
             * acá es donde iría la logica de logIn. Ahora aca solo se toma el nombre de usuario
             */
            String nombreUsuario = reader.readLine();
            server.addUsuarioNombre(nombreUsuario);
 
            String mensajeServer = "Nuevo usuario conectado: " + nombreUsuario;
            server.broadcast(mensajeServer, this);
 
            String mensajeCliente;
 
            //scribe siempre y cuando el mensaje escrito no sea desconectar
            //en el merge con UI, el while es mientras esté logueado o no mande un request de desconección
            //A DEBATIR
            do {
            	mensajeCliente = reader.readLine();
            	mensajeServer = "[" + nombreUsuario + "]: " + mensajeCliente;
                server.broadcast(mensajeServer, this);
 
            } while (!mensajeCliente.equals("desconectar"));
 
            //mata le sucket
            server.removeUsuario(nombreUsuario, this);
            socket.close();
 
            mensajeServer = nombreUsuario + " se desconectó.";
            server.broadcast(mensajeServer, this);
 
        } catch (IOException ex) {
            System.out.println("Error UsuarioThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /*
     * imprime los usarios conectados. Vuela en el merge con UI
     */
    void usuariosConectados() {
        if (server.tieneUsuarios()) {
            writer.println("Usuarios conectados: " + server.getUsuarioNombres());
        } else {
            writer.println("No hay usuarios conectados");
        }
    }
 
    /*
     * este es el metodo que envia mensaje.
     */
    void enviarMensaje(String message) {
        writer.println(message);
    }
}
