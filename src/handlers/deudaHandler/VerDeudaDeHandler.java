package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class VerDeudaDeHandler  extends AsistantSentenceHandler{
	public VerDeudaDeHandler() {
		patron = Pattern.compile("cuánto me debe @([a-z]+)\\?");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			matcher.find();
			String deudor = matcher.group(1);
			return "¡Hola, @" + nombreUsuario + "!";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
