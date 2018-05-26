package asistente;

import java.text.ParseException;

import handlers.*;
import tests.*; //IMPORTANTE------- Esta clase no debería incluir ningun tipo de test. Se deja por ahora. Se debe cambiar

public class Asistente {
	
	private String nombre;		 
	
	public Asistente(){
		this("Shannon");
	}
	public Asistente(String nombre){
		this.nombre = nombre;
	}
	
	public String escuchar(String mensaje) throws ParseException{	
		if(!mensaje.contains("@"+nombre))
			return "";				
		
		AsistantSentenceHandler conversor = new ConversorHandler();
		AsistantSentenceHandler calculoMatematico = new CalculoHandler();
		AsistantSentenceHandler diferenciaFechas = new DiferenciaFechasHandler();
		AsistantSentenceHandler tiempoActual =  new TiempoActualHandler();
		AsistantSentenceHandler saludo =  new SaludoHandler();
		AsistantSentenceHandler defaultResponse = new DefaultHandler();		
		
		conversor.setNextAction(calculoMatematico);
		calculoMatematico.setNextAction(diferenciaFechas);
		diferenciaFechas.setNextAction(tiempoActual);
		tiempoActual.setNextAction(saludo);
		saludo.setNextAction(defaultResponse);
		
		return conversor.giveAnswer(mensaje, TestAsistente.USUARIO); //Este TestAsistente.USUARIO no debe quedar hardcodeado				
	}
}
