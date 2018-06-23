package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class VerDeudaDeHandler  extends AsistantSentenceHandler{
	public VerDeudaDeHandler() {
		patron = Pattern.compile(".+cuánto me debe @([a-z]+)\\?");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String deudor = matcher.group(1);
			
			return verDeuda(nombreUsuario, deudor);
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}

	private String verDeuda(String prestamista,String deudor) {
		String respuesta = "@" + prestamista;
		DeudaAsistente deuda = DeudaController.buscarDeuda(prestamista, deudor);
		if(deuda != null && deuda.getValor() > 0) {
			respuesta += " @" + deudor + " te debe $" + deuda.getValor();
		}else{
			respuesta += " @" + deudor + " no te debe nada.";
			deuda = DeudaController.buscarDeuda(deudor, prestamista);
			if(deuda != null && deuda.getValor() > 0) {
				respuesta += " Vos le debés $" + deuda.getValor();
			}
		}

		return respuesta;
	}
}