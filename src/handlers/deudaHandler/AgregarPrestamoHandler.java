package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class AgregarPrestamoHandler extends AsistantSentenceHandler{
	public AgregarPrestamoHandler() {
		patron = Pattern.compile(".+@([a-z]+) me debe \\$([0-9]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			matcher.find();
			String deudor = matcher.group(1);
			int valor = Integer.parseInt(matcher.group(2));
			return "¡Hola, @" + nombreUsuario + "!";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
