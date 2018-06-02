package chat.cliente;

//import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEscritura extends Thread {
	 	private PrintWriter writer;
	    private Socket socket;
	    private Cliente cliente;
	 
	    public ThreadEscritura(Socket socket, Cliente cliente) {
	        this.socket = socket;
	        this.cliente = cliente;
	 
	        try {
	            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
	            writer = new PrintWriter(output, true);
	        } catch (IOException ex) {
	            System.out.println("Error obteniendo output stream: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	 
	    public void run() {
	 
	        //Console console = System.console();
	    	Scanner console = new Scanner(System.in);

	        //String nombreUsuario = console.readLine("\nIntroduzca su nombre: ");
	    	System.out.println("Introduzca su nombre: \n");
	    	String nombreUsuario = console.nextLine();
	        cliente.setNombreUsuario(nombreUsuario);
	        writer.println(nombreUsuario);
	 
	        String mensaje;
	 
	        do {
	            //mensaje = console.readLine("[" + userName + "]: ");
	        	mensaje = console.nextLine();
	            writer.println(mensaje);
	 
	        } while (!mensaje.equals("desconectar"));
	 
	        try {
	        	console.close();
	            socket.close();
	        } catch (IOException ex) {
	 
	            System.out.println("Error escribiendo en el servidor: " + ex.getMessage());
	        }
	    }
}
