package handlers;

import handlers.deudaHandler.*;
import tests.TestAsistente;

public class DeudaHandler extends AsistantSentenceHandler{

	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		String respuesta;
		
		
		AsistantSentenceHandler agregarDeuda = new AgregarDeudaHandler();
		AsistantSentenceHandler agregarPrestamo = new AgregarPrestamoHandler();
		AsistantSentenceHandler pagarDeuda = new PagoDeudaHandler();
		AsistantSentenceHandler reciboPago = new ReciboPagoHandler();
		AsistantSentenceHandler verDeudaDe = new VerDeudaDeHandler();
		AsistantSentenceHandler verDeudaCon = new VerDeudaConHandler();
		AsistantSentenceHandler gastoCompartido = new DeudaGrupalCasoUnoHandler();
		AsistantSentenceHandler estado = new EstadoDeudaHandler();
		AsistantSentenceHandler simplificar = new SimplificarDeudaHandler();
		AsistantSentenceHandler deudaDefaultResponse = new DeudaDefaultHandler();
		
		agregarDeuda.setNextAction(agregarPrestamo);
		agregarPrestamo.setNextAction(pagarDeuda);
		pagarDeuda.setNextAction(reciboPago);
		reciboPago.setNextAction(verDeudaDe);
		verDeudaDe.setNextAction(verDeudaCon);
		verDeudaCon.setNextAction(simplificar);
		simplificar.setNextAction(gastoCompartido);
		gastoCompartido.setNextAction(estado);
		estado.setNextAction(deudaDefaultResponse);
		
		if(!(respuesta = agregarDeuda.giveAnswer(mensaje.toLowerCase(), TestAsistente.USUARIO)).isEmpty())
			return respuesta;
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
