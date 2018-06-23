package bot.handlers.deudaHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class SimplificarDeudaHandler extends AsistantSentenceHandler {
	public SimplificarDeudaHandler() {
		patron = Pattern.compile(".+simplificar deudas con @([a-z]+) y @([a-z]+)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){
			String[] usuarios = new String[2];
			usuarios[0] = matcher.group(1);
			usuarios[1] = matcher.group(2);
		
			DeudaAsistente deuda = buscarPrestador(usuarios,nombreUsuario);
			
			DeudaAsistente prestamo = buscarDeudor(nombreUsuario,usuarios);
			
			if(deuda == null || prestamo == null)
				return "@" + nombreUsuario + " no se pueden simplificar las deudas";
			DeudaController.pagoDeuda(deuda.getPrestamista(), nombreUsuario, prestamo.getValor());
			DeudaController.pagoDeuda(nombreUsuario, prestamo.getDeudor(), prestamo.getValor());
			DeudaController.agregarDeuda(deuda.getPrestamista(), prestamo.getDeudor(), prestamo.getValor());
			
			return "@" + nombreUsuario + " bueno.";
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	private DeudaAsistente buscarPrestador(String[] usuarios, String deudor) {
		DeudaAsistente deuda = null, deudaAux = null;
		for (int i = 0; i < usuarios.length; i++) {
			deudaAux = DeudaController.buscarDeuda(usuarios[i], deudor);
			if(deudaAux != null && deudaAux.getValor() > 0)
				deuda = deudaAux;
		}
		return deuda;
	}
	
	private DeudaAsistente buscarDeudor(String prestamista, String[] usuarios) {
		DeudaAsistente deuda = null, deudaAux = null;
		for (int i = 0; i < usuarios.length; i++) {
			deudaAux = DeudaController.buscarDeuda(prestamista, usuarios[i]);
			if(deudaAux != null && deudaAux.getValor() > 0)
				deuda = deudaAux;
		}
		return deuda;
	}
}

