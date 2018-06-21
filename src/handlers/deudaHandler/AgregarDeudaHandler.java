package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class AgregarDeudaHandler extends AsistantSentenceHandler {
	public AgregarDeudaHandler() {
		patron = Pattern.compile(".+le debo \\$([0-9]+) a @([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			matcher.find();
			int valor = Integer.parseInt(matcher.group(1));
			String prestador = matcher.group(2);
			
			return "¡Hola, @" + nombreUsuario + "!";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
