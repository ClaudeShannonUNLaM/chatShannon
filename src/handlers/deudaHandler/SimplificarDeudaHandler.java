package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;

public class SimplificarDeudaHandler extends AsistantSentenceHandler {
	public SimplificarDeudaHandler() {
		patron = Pattern.compile(".+simplificar deudas con @([a-z]+) y @([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			matcher.find();
			String[] usuarios = new String[2];
			usuarios[0] = matcher.group(1);
			usuarios[1] = matcher.group(2);
			
			//buscar deudas
			
			//buscar prestamos
			
			//diferencia entre prestamo y deuda.
			
			//eliminacion deudas y prestamos con ambos usuarios.
			
			//guardar prestamo y deuda nuevos, de los usuarios
			
			return "¡Hola, @" + nombreUsuario + "!";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}

