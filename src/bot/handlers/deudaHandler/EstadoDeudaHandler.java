package bot.handlers.deudaHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.handlers.AsistantSentenceHandler;
import chat.serverUtils.Mensaje;
import hibernate.deudaAsistente.DeudaAsistente;
import hibernate.deudaAsistente.DeudaController;

public class EstadoDeudaHandler extends AsistantSentenceHandler{
	public EstadoDeudaHandler() {
		patron = Pattern.compile(".+cual es mi estado de deudas\\?");
	}

	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);	
		Mensaje msj=new Mensaje();
		if(matcher.matches()){
			String respuesta = "@" + nombreUsuario;
			List<DeudaAsistente> prestamos = DeudaController.buscarDeudas(nombreUsuario,"");
			List<DeudaAsistente> deudas = DeudaController.buscarDeudas("",nombreUsuario);
			
			if(prestamos == null && deudas == null)
			{
				msj.setDescripcion(respuesta + " no debe ni le deben nada.");
				return msj;
			}
			if(deudas != null)
				for(int i = 0; i < deudas.size(); i++)
					respuesta += " le debés $" + deudas.get(i).getValor() + " a @" + deudas.get(i).getPrestamista() + ".";

			if(prestamos != null)
				for(int i = 0; i < prestamos.size(); i++)
					respuesta += " @" + prestamos.get(i).getDeudor() + " te debe $" + prestamos.get(i).getValor() + ".";
			
			msj.setDescripcion(respuesta);
			return msj;
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}	
}
