package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaController;

public class PagoDeudaHandler extends AsistantSentenceHandler{
	public PagoDeudaHandler() {
		patron = Pattern.compile(".+le pagué a @([a-z]+) \\$([0-9]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String prestamista = matcher.group(1);
			int pago = Integer.parseInt(matcher.group(2));
			DeudaController.pagoDeuda(prestamista, nombreUsuario, pago);
			return "@" + nombreUsuario + " anotado.";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
}
