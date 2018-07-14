package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;

public class SaludoHandler extends AsistantSentenceHandler{
	
	public SaludoHandler(){		
		patron = Pattern.compile("(?:hola|buenos dias|todo bien|saludos|che|oye).*@([a-z]+)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);
		Mensaje msj ;
		if(matcher.matches()){			
			msj=new Mensaje("¡Hola, @" + nombreUsuario + "!");
			return 	msj;									
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);		
	}	
	
}
