package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;

public class AgradecimientoHandler extends AsistantSentenceHandler{
	

	public AgradecimientoHandler(){
		patron = Pattern.compile(".*(Gracias|gracias)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);	
		Mensaje msj=new Mensaje();
	    if (matcher.find()) {	    	
	    	msj.setDescripcion("No es nada, @" + nombreUsuario);
	    	return msj;
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}		
}
