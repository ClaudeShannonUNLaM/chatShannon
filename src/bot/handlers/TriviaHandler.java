package bot.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;

public class TriviaHandler extends AsistantSentenceHandler {

	private String[] trivias = {
			"Cleopatra vivió más cerca de la invención del iPhone que de la construcción de la Gran Pirámide.",
			"Rusia tiene una superficie más grande que Plutón.",
			"Arabia Saudí importa camellos de Australia.",
			"El nombre completo del juguete de Barbie es Barbara Millicent Roberts.",
			"Woody, de Toy Story, tiene un nombre completo y es Woody Pride.",			
			"Las zanahorias eran originalmente de color púrpura.",
			"El corazón de una ballena azul es tan grande que un ser humano podría nadar a través de las arterias.",
			"Una maquina expendedora puede matarte más fácilmente que un tiburón.",
			"La Universidad de Oxford es más antigua que el imperio azteca.",
			"Francia seguía ejecutando personas con una guillotina cuando la primera película de Star Wars se estrenó.",
			"Los armadillos casi siempre dan a luz a cuatrillizos idénticos.",
			"Eduard Punset es en realidad más viejo que el pan de molde.",
			"El unicornio es el animal nacional de Escocia."
	};
	
	private File archivo;	
	
	public TriviaHandler(){
		archivo = new File("TriviaHandler.txt");
		patron = Pattern.compile(".*(?:dame|quiero).*(?:trivia|interesante|interesantes|importante|importantes)");
	}	
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {	
		Matcher matcher = patron.matcher(mensaje);
		Mensaje msj=new Mensaje();
	    if (matcher.matches()) {
	    	Scanner s = null;
			try {
				String respuesta = "";
				s = new Scanner(archivo);
				int TriviaIndex = s.nextInt();
				s.close();
				
				if(TriviaIndex == trivias.length){
					TriviaIndex = 1;					
					respuesta = trivias[0];
				}
				else{
					TriviaIndex++;
					respuesta = trivias[TriviaIndex - 1];
				}			
							
				PrintWriter pw = new PrintWriter(archivo);
				pw.print(TriviaIndex);
				pw.close();
				msj.setDescripcion(respuesta);
				return msj;
								
			} catch (FileNotFoundException e) {
				msj.setDescripcion("Hubo un error abriendo el archivo");
				return msj;
			}	        		    	
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);	
	}	
}
