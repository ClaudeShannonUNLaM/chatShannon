package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import chat.serverUtils.Mensaje;
import hibernate.deudaAsistente.DeudaController;

public class ReciboPagoHandler extends AsistantSentenceHandler{
	public ReciboPagoHandler() {
		patron = Pattern.compile(".+@([a-z]+) me pagó \\$([0-9]+)");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		Mensaje msj;
		
		if(matcher.matches()){
			String deudor = matcher.group(1);
			int pago = Integer.parseInt(matcher.group(2));
			DeudaController.pagoDeuda(nombreUsuario, deudor, pago);
			msj=new Mensaje("@" + nombreUsuario + " anotado.");
			return msj;
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
}
