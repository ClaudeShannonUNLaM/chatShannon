package chat.clienteUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.junit.runner.Request;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import chat.serverUtils.Mensaje;
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
				
				//WorkArround al cambio de clases de GSON.
				if((boolean)response.getDatos().get("exito")) {
					
					LinkedTreeMap<String, Object> usu = (LinkedTreeMap<String, Object>) response.getDatos().get("usuario"); 
					Usuario usuario = new Usuario();
					
					double id = (double)usu.get("id");
					
					usuario.setId((int)id) ;
					usuario.setNombre((String)usu.get("nombre"));
					usuario.setPassword((String)usu.get("password"));
					usuario.setOnline((boolean)usu.get("online"));
					
					((Login)pantallas.get("login")).
					IngresarSistema(usuario, true);	
				}
				else {
					((Login)pantallas.get("login")).
					IngresarSistema(new Usuario(), false);
				}
					
				break;
			case "cargaInicial":			
				
				//WorkArround al cambio de clases de GSON.
				ArrayList<LinkedTreeMap<String, Object>> mapPublicas = (ArrayList<LinkedTreeMap<String, Object>>) response.getDatos().get("salasPublicas");
				ArrayList<LinkedTreeMap<String, Object>> mapPrivadas = (ArrayList<LinkedTreeMap<String, Object>>) response.getDatos().get("salasPublicas");
				ArrayList<LinkedTreeMap<String, Object>> mapContactos = (ArrayList<LinkedTreeMap<String, Object>>) response.getDatos().get("salasPublicas");
				ArrayList<Sala> publicas = new ArrayList<Sala>();
				ArrayList<Sala> privadas = new ArrayList<Sala>();
				ArrayList<Usuario> contactos = new ArrayList<Usuario>();
				
				for (LinkedTreeMap<String, Object> dato : mapPublicas) {
					double id = (double)dato.get("id");
					Sala nuevaSala = new Sala((int)id,(String)dato.get("nombre"),false);
					publicas.add(nuevaSala);
				}
				
				for (LinkedTreeMap<String, Object> dato : mapPrivadas) {
					double id = (double)dato.get("id");
					Sala nuevaSala = new Sala((int)id,(String)dato.get("nombre"),true);
					privadas.add(nuevaSala);
				}				
				
				/*for (LinkedTreeMap<String, Object> dato : mapContactos) {
					
				}*/
				
				
				((Index)pantallas.get("index")).
				cargarDatosIndex(publicas,privadas,contactos);
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

				//WorkArround al cambio de clases de GSON.
				LinkedTreeMap<String, Object> men = (LinkedTreeMap<String, Object>) response.getDatos().get("usuario"); 
				Mensaje mensaje = (Mensaje)response.getDatos().get("mensaje");
				
				if(mensaje.getSala() != null) //El mensaje es para una sala.
					((Index)pantallas.get("index")).
					agregarMensajeSala(mensaje);
				else					
					((Index)pantallas.get("index")). //El mensaje es para uncontacto.
					agregarMensajeContacto(mensaje);
				
				break;
				
		}
		
	}
}
