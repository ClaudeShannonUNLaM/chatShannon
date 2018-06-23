package handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class VerDeudaConHandler extends AsistantSentenceHandler {
	public VerDeudaConHandler() {
		patron = Pattern.compile(".+cuánto le debo a @([a-z]+)\\?");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String prestamista = matcher.group(1);
			
			return verDeuda(prestamista, nombreUsuario);
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	private String verDeuda(String prestamista,String deudor) {
		String respuesta = "@" + deudor;
		DeudaAsistente deuda = DeudaController.buscar(prestamista, deudor);
		if(deuda != null && deuda.getValor() > 0) {
			respuesta += " debés $" + deuda.getValor() + " a @" + prestamista;
		}else{
			respuesta += " no le debés nada.";
			deuda = DeudaController.buscar(deudor, prestamista);
			if(deuda != null && deuda.getValor() > 0) {
				respuesta += " @" + prestamista + " te debe $" + deuda.getValor();
			}
		}

		return respuesta;
	}
}
