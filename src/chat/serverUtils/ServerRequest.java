package chat.serverUtils;

import java.util.HashMap;

public class ServerRequest {

	private HashMap<String, String> datos;
	private FuncionalidadServerEnum funcionalidad;
	
	public ServerRequest(HashMap<String, String> map, FuncionalidadServerEnum funcion) {
		datos = map;
		funcionalidad = funcion;
	}
	
	public HashMap<String, String> getDatos() {
		return datos;
	}

	public FuncionalidadServerEnum getFuncionalidad() {
		return funcionalidad;
	}	
}
