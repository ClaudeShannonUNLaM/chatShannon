package chat.serverUtils;

import java.util.HashMap;

public class ServerResponse {	
	
	private HashMap<String, Object> datos;

	public ServerResponse(HashMap<String, Object> datos) {
		this.datos = datos;
	}
	
	public HashMap<String, Object> getDatos() {
		return datos;
	}
}
