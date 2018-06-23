package handlers;

public class LeyesRoboticaHandler extends AsistantSentenceHandler {

	
	private String leyesRobotica="Las Leyes de la Robotica son: " + "1) Un robot no har� da�o a un ser humano o, por inacci�n, permitir que un ser humano sufra da�o."
			+ "2) Un robot debe obedecer las �rdenes dadas por los seres humanos, excepto si estas �rdenes entrasen en conflicto con la 1� Ley."
			+ "3) Un robot debe proteger su propia existencia en la medida en que esta protecci�n no entre en conflicto con la 1� o la 2� Ley.";
	
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