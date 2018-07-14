package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import chat.serverUtils.Mensaje;
import hibernate.deudaAsistente.DeudaController;

public class PagoDeudaHandler extends AsistantSentenceHandler{
	public PagoDeudaHandler() {
		patron = Pattern.compile(".+le pagué a @([a-z]+) \\$([0-9]+)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		Mensaje msj=new Mensaje();
		if(matcher.matches()){
			String prestamista = matcher.group(1);
			int pago = Integer.parseInt(matcher.group(2));
			DeudaController.pagoDeuda(prestamista, nombreUsuario, pago);
			msj.setDescripcion("@" + nombreUsuario + " anotado.");
			return msj;
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
}
