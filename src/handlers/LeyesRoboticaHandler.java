package handlers;

public class LeyesRoboticaHandler extends AsistantSentenceHandler {

	
	private String leyesRobotica="Las Leyes de la Robotica son: " + "1) Un robot no hará daño a un ser humano o, por inacción, permitir que un ser humano sufra daño."
			+ "2) Un robot debe obedecer las órdenes dadas por los seres humanos, excepto si estas órdenes entrasen en conflicto con la 1ª Ley."
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la 1ª o la 2ª Ley.";
	
	private String[] palabrasClaveRobotica = {"leyes de la robotica","leyes de robotica","LEYES DE LA ROBOTICA"};	
	
	
	public LeyesRoboticaHandler() {
		
	}
	
	public String giveAnswer(String mensaje, String nombreUsuario) {
		
		
		for(int i=0;i<palabrasClaveRobotica.length;i++)
		{
			if(mensaje.contains(palabrasClaveRobotica[i])) {
			return leyesRobotica;
			}
		}
		return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	
}