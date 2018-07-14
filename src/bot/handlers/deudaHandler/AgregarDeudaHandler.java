package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bot.handlers.AsistantSentenceHandler;
import chat.serverUtils.Mensaje;
import dataBaseConection.DataBaseHelper;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class AgregarDeudaHandler extends AsistantSentenceHandler {
	public AgregarDeudaHandler() {
		patron = Pattern.compile(".+le debo \\$([0-9]+) a @([a-z]+)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		Mensaje msj=new Mensaje();
		if(matcher.matches()){
			int valor = Integer.parseInt(matcher.group(1));
			String prestamista = matcher.group(2);
			DeudaController.agregarDeuda(prestamista, nombreUsuario, valor);
			msj.setDescripcion("@" + nombreUsuario + " anotado.");
			return msj;
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	

}
