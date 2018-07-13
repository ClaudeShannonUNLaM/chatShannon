package bot.asistente;

import java.io.IOException;
import java.text.ParseException;

import bot.handlers.*;
import tests.*; //IMPORTANTE------- Esta clase no debería incluir ningun tipo de test. Se deja por ahora. Se debe cambiar

public class Asistente {
	
	private String nombre;		 
	
	public Asistente(){
		this("Shannon");
	}
	public Asistente(String nombre){
		this.nombre = nombre;
	}
	
	public String escuchar(String mensaje) throws ParseException, IOException{	
		if(!mensaje.contains("@"+nombre))
			return "";				
		
		AsistantSentenceHandler conversor = new ConversorHandler();
		AsistantSentenceHandler chuckNorris = new ChuckNorrisHandler();
		AsistantSentenceHandler trivia = new TriviaHandler();
		AsistantSentenceHandler serAdivinadoMayorMenor = new MayorMenorAdivinadoHandler();
		AsistantSentenceHandler adivinarMayorMenor= new AdivinarMayorMenorHandler();
		AsistantSentenceHandler calculoMatematico = new CalculoHandler();
		AsistantSentenceHandler diferenciaFechas = new DiferenciaFechasHandler();
		AsistantSentenceHandler tiempoActual =  new TiempoActualHandler();
		AsistantSentenceHandler saludo =  new SaludoHandler();
		AsistantSentenceHandler defaultResponse = new DefaultHandler();		
		AsistantSentenceHandler agradecer = new AgradecimientoHandler();
		AsistantSentenceHandler leyesRobotica = new LeyesRoboticaHandler();
		AsistantSentenceHandler deuda = new DeudaHandler();
		AsistantSentenceHandler datosFinancieros = new DatosFinancierosHandler();
		AsistantSentenceHandler memes = new MostrarMemeHandler();	
		AsistantSentenceHandler youtubeHandler = new YoutubeHandler();
		
		agradecer.setNextAction(leyesRobotica);
		leyesRobotica.setNextAction(youtubeHandler);
		youtubeHandler.setNextAction(calculoMatematico);
		calculoMatematico.setNextAction(adivinarMayorMenor);
		adivinarMayorMenor.setNextAction(serAdivinadoMayorMenor);
		serAdivinadoMayorMenor.setNextAction(conversor);
		conversor.setNextAction(chuckNorris);
		chuckNorris.setNextAction(trivia);
		trivia.setNextAction(diferenciaFechas);
		diferenciaFechas.setNextAction(tiempoActual);
		tiempoActual.setNextAction(deuda);
		deuda.setNextAction(datosFinancieros);
		datosFinancieros.setNextAction(memes);
		memes.setNextAction(saludo);
		saludo.setNextAction(defaultResponse);
		
		return agradecer.giveAnswer(mensaje.toLowerCase(), TestAsistente.USUARIO); //Este TestAsistente.USUARIO no debe quedar hardcodeado				
	}
	
}
