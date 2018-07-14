package bot.asistente;

import java.io.IOException;
import java.text.ParseException;

import bot.handlers.*;
import chat.serverUtils.Mensaje;
//import tests.*; //IMPORTANTE------- Esta clase no debería incluir ningun tipo de test. Se deja por ahora. Se debe cambiar
import tests.TestAsistente;


public class Asistente {
	
	private String nombre;		 
	
	public Asistente(){
		this("Shannon");
	}
	public Asistente(String nombre){
		this.nombre = nombre;
	}
	
	public Mensaje escuchar(String mensaje, String emisor) throws ParseException, IOException{	
		if(!mensaje.contains("@"+nombre))
			return null;				
		
		AsistantSentenceHandler rssAgregar = new AgregarRSSHandler();
		AsistantSentenceHandler rss = new RssHandler();
		AsistantSentenceHandler gag = new Meme9GagHandler();
		AsistantSentenceHandler clima = new ClimaHandler();
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
		
		AsistantSentenceHandler memes = new MostrarMemeHandler();	
		AsistantSentenceHandler youtubeHandler = new YoutubeHandler();
		AsistantSentenceHandler buscadorWikiGoogle= new BuscadorWikiGoogleHandler();
		
		rssAgregar.setNextAction(rss);
		rss.setNextAction(gag);
		gag.setNextAction(clima);
		clima.setNextAction(agradecer);
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
		deuda.setNextAction(buscadorWikiGoogle);
		buscadorWikiGoogle.setNextAction(memes);
		memes.setNextAction(saludo);
		saludo.setNextAction(defaultResponse);
		
		return rssAgregar.giveAnswer(mensaje.toLowerCase(),emisor); //Este TestAsistente.USUARIO no debe quedar hardcodeado				
		//return agradecer.giveAnswer(mensaje.toLowerCase(), emisor);				
	}
}
