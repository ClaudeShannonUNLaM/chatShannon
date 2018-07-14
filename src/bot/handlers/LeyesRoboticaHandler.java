package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;

public class LeyesRoboticaHandler extends AsistantSentenceHandler {

	
private String leyesRobotica="Las Leyes de la Robotica son: " + "1) Un robot no hará daño a un ser humano o, por inacción, permitir que un ser humano sufra daño."
			+ "2) Un robot debe obedecer las órdenes dadas por los seres humanos, excepto si estas órdenes entrasen en conflicto con la 1º Ley."
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la 1º o la 2º Ley.";	
	private String[] palabrasClaveRobotica = {"leyes de la robotica","leyes de robotica","LEYES DE LA ROBOTICA"};	
	
	
	public LeyesRoboticaHandler() {
		patron = Pattern.compile(".*(?:decime|Decime|cuales|Cuales|Cu�les).*(?:leyes de la robotica|Leyes de la Robotica).*");
	}
	
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Mensaje msj=new Mensaje();
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.matches()) {
	    	msj.setDescripcion(leyesRobotica);
	    	return msj;
	    }
		return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	
}