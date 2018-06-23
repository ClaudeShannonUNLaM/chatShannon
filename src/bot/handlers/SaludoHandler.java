package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaludoHandler extends AsistantSentenceHandler{
	
	public SaludoHandler(){		
		patron = Pattern.compile("(?:hola|buenos dias|todo bien|saludos|che|oye).*@([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){			
			return "¡Hola, @" + nombreUsuario + "!";										
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);		
	}	
	
}
