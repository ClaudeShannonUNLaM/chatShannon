package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class EstadoDeudaHandler extends AsistantSentenceHandler{
	public EstadoDeudaHandler() {
		patron = Pattern.compile(".+cual es mi estado de deudas");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			/*for(DeudaAsistente x: prestamos)
			 	respuesta += "Me debe 20p  @pepe.";
			  for(DeudaAsistente x: deudas)
			 	respuesta += "Le debo 20p a @pepe.";
			 */
			return "¡Hola, @" + nombreUsuario + "!";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
