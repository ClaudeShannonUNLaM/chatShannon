package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataBaseConection.DataBaseHelper;
import handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class AgregarDeudaHandler extends AsistantSentenceHandler {
	public AgregarDeudaHandler() {
		patron = Pattern.compile(".+le debo \\$([0-9]+) a @([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			int valor = Integer.parseInt(matcher.group(1));
			String prestamista = matcher.group(2);
			DeudaController.agregarDeuda(prestamista, nombreUsuario, valor);
			
			return "@" + nombreUsuario + " anotado.";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	

}
