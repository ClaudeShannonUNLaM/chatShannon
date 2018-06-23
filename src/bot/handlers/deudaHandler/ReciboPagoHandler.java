package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaController;

public class ReciboPagoHandler extends AsistantSentenceHandler{
	public ReciboPagoHandler() {
		patron = Pattern.compile(".+@([a-z]+) me pagó \\$([0-9]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String deudor = matcher.group(1);
			int pago = Integer.parseInt(matcher.group(2));
			DeudaController.pagoDeuda(nombreUsuario, deudor, pago);
			return "@" + nombreUsuario + " anotado.";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
}
