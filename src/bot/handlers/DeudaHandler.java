package bot.handlers;

import bot.handlers.deudaHandler.*;
import chat.serverUtils.Mensaje;
import tests.TestAsistente;

public class DeudaHandler extends AsistantSentenceHandler{

	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		String respuesta;
		Mensaje msj=new Mensaje();
		
		AsistantSentenceHandler agregarDeuda = new AgregarDeudaHandler();
		AsistantSentenceHandler agregarPrestamo = new AgregarPrestamoHandler();
		AsistantSentenceHandler pagarDeuda = new PagoDeudaHandler();
		AsistantSentenceHandler reciboPago = new ReciboPagoHandler();
		AsistantSentenceHandler verDeudaDe = new VerDeudaDeHandler();
		AsistantSentenceHandler verDeudaCon = new VerDeudaConHandler();
		AsistantSentenceHandler gastoCompartido = new DeudaGrupalCasoUnoHandler();
		AsistantSentenceHandler gastoCompartidoDos = new DeudaGrupalCasoDosHandler();
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
		gastoCompartido.setNextAction(gastoCompartidoDos);
		gastoCompartidoDos.setNextAction(estado);
		estado.setNextAction(deudaDefaultResponse);

		if((msj = agregarDeuda.giveAnswer(mensaje.toLowerCase(), TestAsistente.USUARIO))!= null)
			return msj;
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
