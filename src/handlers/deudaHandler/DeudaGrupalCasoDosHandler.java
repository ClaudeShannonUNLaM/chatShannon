package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaController;

public class DeudaGrupalCasoDosHandler extends AsistantSentenceHandler{
	public DeudaGrupalCasoDosHandler() {
		patron = Pattern.compile(".+con @([a-z]+) y @([a-z]+) gastamos \\$([0-9]+) y pagué yo");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String deudor = matcher.group(1);
			String deudor2 = matcher.group(2);
			int valor = Integer.parseInt(matcher.group(3));
			valor /= 3;
			DeudaController.agregarDeuda(nombreUsuario, deudor, valor);
			DeudaController.agregarDeuda(nombreUsuario, deudor2, valor);
			
			return "@" + nombreUsuario + " anotado.";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
