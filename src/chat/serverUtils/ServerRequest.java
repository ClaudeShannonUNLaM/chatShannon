package chat.serverUtils;

import java.util.HashMap;

public class ServerRequest {

	private HashMap<String, Object> datos;
	private FuncionalidadServerEnum funcionalidad;
	
	public ServerRequest(HashMap<String, Object> map, FuncionalidadServerEnum funcion) {
		datos = map;		
		funcionalidad = funcion;
	}
	
	public HashMap<String, Object> getDatos() {
		return datos;
	}

	public FuncionalidadServerEnum getFuncionalidad() {
		return funcionalidad;
	}	
}
