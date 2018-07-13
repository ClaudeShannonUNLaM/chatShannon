package chat.clienteUtils;

import chat.serverUtils.ServerResponse;

public abstract class ResponseHandler {
	
	public abstract Object atenderSolicitud(ServerResponse response);
	

}
