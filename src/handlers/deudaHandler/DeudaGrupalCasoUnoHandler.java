package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class DeudaGrupalCasoUnoHandler extends AsistantSentenceHandler{
	public DeudaGrupalCasoUnoHandler() {
		patron = Pattern.compile(".+con @([a-z]+) y @([a-z]+) gastamos \\$([0-9]+) y pagó @([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			matcher.find();
			/*String deudor = matcher.group(1);
			String deudor2 = matcher.group(2);
			int valor = Integer.parseInt(matcher.group(3));
			String prestamista = matcher.group(4);
			valor /= 3;*/
			
			return "@" + nombreUsuario + " bueno";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
