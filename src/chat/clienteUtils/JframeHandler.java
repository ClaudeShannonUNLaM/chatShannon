package chat.clienteUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.junit.runner.Request;

import chat.serverUtils.ServerResponse;
import chat.cliente.userInterface.*;
import hibernate.sala.Sala;
import hibernate.usuario.Usuario;

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
				IngresarSistema((Usuario)response.getDatos().get("usuario"),
								(boolean)response.getDatos().get("exito"));	
				break;
			case "cargaInicial":
				((Index)pantallas.get("index")).
				cargarDatosIndex((List<Sala>)response.getDatos().get("salasPublicas"),
						(List<Sala>)response.getDatos().get("salasPrivadas"),
						(List<Usuario>)response.getDatos().get("contactos"));
				break;
				
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
			case "mensajeRecivido":
				return;
				
		}
		
	}
}
