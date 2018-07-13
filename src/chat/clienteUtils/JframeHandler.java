package chat.clienteUtils;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.junit.runner.Request;

import chat.serverUtils.ServerResponse;
import chat.cliente.userInterface.*;
public class JframeHandler { //Se encarga de distribuir la info que devuelve
							 //el server por las distintas pantallas
	private HashMap<String, JFrame> pantallas;

	public JframeHandler() {
		pantallas = new HashMap<String, JFrame>();
	}
	
	public HashMap<String, JFrame> getPantallas() {
		return pantallas;
	}

	public void addPantalla(String nombrePantalla,JFrame pantalla) {
		if(!pantallas.containsKey(nombrePantalla))
			pantallas.put(nombrePantalla, pantalla);
	}	
	
	public void atender(ServerResponse response) throws IOException {		
		String funcionalidad = (String)response.getDatos().get("funcionalidad");	
		
		switch (funcionalidad) {
			case "login":
				((Login)pantallas.get("login")).
				IngresarSistema((String)response.getDatos().get("nombreusuario"),
								(boolean)response.getDatos().get("exito"));	
				break;
			/*case "cargaInicial":
				((Index)pantallas.get("index")).
				IngresarSistema((String)response.getDatos().get("nombreusuario"),
								(boolean)response.getDatos().get("exito"));
				break;
				*/
			case "nuevoUsuario":
				((NuevoUsuario)pantallas.get("nuevoUsuario")).
				notificarCreacionNuevoUsuario((boolean)response.getDatos().get("exito"));
				break;
			case "nuevaSala":
				((NuevaSala)pantallas.get("nuevaSala")).
				informarCreacionSala((boolean)response.getDatos().get("exito"));
				break;				
			case "nuevoContacto":
				((AgregarContacto)pantallas.get("nuevoContacto")).
				informarAgregadoContacto((boolean)response.getDatos().get("exito"));
				
				break;

		default:
			break;
		}
		
	}
}
