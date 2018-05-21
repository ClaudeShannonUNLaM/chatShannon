package handlers;

public class SaludoHandler extends AsistantSentenceHandler{	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		if(true){ //if(mensaje cumple con expresion regular)			
			return "¡Hola, @" + nombreUsuario + "!";										
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);		
	}	
	
}
