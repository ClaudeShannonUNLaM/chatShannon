package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriviaHandler extends AsistantSentenceHandler {

	private String[] trivias = {
			"Cleopatra vivi� m�s cerca de la invenci�n del iPhone que de la construcci�n de la Gran Pir�mide.",
			"Rusia tiene una superficie m�s grande que Plut�n.",
			"Arabia Saud� importa camellos de Australia.",
			"El nombre completo del juguete de Barbie es Barbara Millicent Roberts.",
			"Woody, de Toy Story, tiene un nombre completo y es Woody Pride.",			
			"Las zanahorias eran originalmente de color p�rpura.",
			"El coraz�n de una ballena azul es tan grande que un ser humano podr�a nadar a trav�s de las arterias.",
			"Una maquina expendedora puede matarte m�s f�cilmente que un tibur�n.",
			"La Universidad de Oxford es m�s antigua que el imperio azteca.",
			"Francia segu�a ejecutando personas con una guillotina cuando la primera pel�cula de Star Wars se estren�.",
			"Los armadillos casi siempre dan a luz a cuatrillizos id�nticos.",
			"Eduard Punset es en realidad m�s viejo que el pan de molde.",
			"El unicornio es el animal nacional de Escocia."
	};
	
	private int triviaIndex = 0;	
	
	
	public TriviaHandler(){
		triviaIndex = 0;
		patron = Pattern.compile(".*(?:dame|quiero).*(?:trivia|interesante|interesantes|importante|importantes)");
	}	
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {	
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.matches()) {	    	
	    	triviaIndex ++;
			if(triviaIndex == trivias.length){
				triviaIndex = 0;
				return trivias[trivias.length - 1];
			}			
			return trivias[triviaIndex - 1];
	    	
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);	
		
	}	
}
