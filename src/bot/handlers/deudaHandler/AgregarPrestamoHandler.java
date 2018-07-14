package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import chat.serverUtils.Mensaje;
import hibernate.deudaAsistente.DeudaController;

public class AgregarPrestamoHandler extends AsistantSentenceHandler{
	public AgregarPrestamoHandler() {
		patron = Pattern.compile(".+@([a-z]+) me debe \\$([0-9]+)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);
		Mensaje msj=new Mensaje();
		
		if(matcher.matches()){
			//matcher.find();
			String deudor = matcher.group(1);
			int valor = Integer.parseInt(matcher.group(2));
			
			DeudaController.agregarDeuda(nombreUsuario,deudor,valor);
			msj.setDescripcion("@" + nombreUsuario + " anotado.");
			return msj;
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
}
