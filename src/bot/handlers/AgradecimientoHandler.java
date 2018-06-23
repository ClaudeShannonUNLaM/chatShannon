package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgradecimientoHandler extends AsistantSentenceHandler{
	

	public AgradecimientoHandler(){
		patron = Pattern.compile(".*(Gracias|gracias)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.find()) {	    	
	    	return "No es nada, @" + nombreUsuario;
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}		
}
