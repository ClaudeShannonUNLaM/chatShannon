package handlers;

import handlers.deudaHandler.*;
import tests.TestAsistente;

public class DeudaHandler extends AsistantSentenceHandler{

	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		String respuesta;
		
		
		AsistantSentenceHandler agregarDeuda = new AgregarDeudaHandler();
		AsistantSentenceHandler agregarPrestamo = new AgregarPrestamoHandler();
		AsistantSentenceHandler verDeudaDe = new VerDeudaDeHandler();
		AsistantSentenceHandler gastoCompartido = new DeudaCompartidaHandler();
		AsistantSentenceHandler estado = new EstadoDeudaHandler();
		AsistantSentenceHandler simplificar = new SimplificarDeudaHandler();
		AsistantSentenceHandler deudaDefaultResponse = new DeudaDefaultHandler();
		
		agregarDeuda.setNextAction(agregarPrestamo);
		agregarPrestamo.setNextAction(verDeudaDe);
		verDeudaDe.setNextAction(simplificar);
		simplificar.setNextAction(gastoCompartido);
		gastoCompartido.setNextAction(estado);
		estado.setNextAction(deudaDefaultResponse);
		
		if(!(respuesta = agregarDeuda.giveAnswer(mensaje.toLowerCase(), TestAsistente.USUARIO)).isEmpty())
			return respuesta;
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
