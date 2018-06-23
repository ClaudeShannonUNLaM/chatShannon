package handlers;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tests.RF10Tests;

public class MayorMenorAdivinadoHandler  extends AsistantSentenceHandler{
	private int respuesta;
//	private int conteoIntentos;

	public MayorMenorAdivinadoHandler () {
		patron = Pattern.compile(".*(del 1 al 100|es el|fue divertido).*");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if (matcher.matches()) {

			switch (matcher.group(1)) { 
				case "del 1 al 100" :
				{

					Random rnd = new Random();
					this.respuesta = (int)(rnd.nextDouble() * 100 + 0);
//					conteoIntentos=0;
		    		return "@"+nombreUsuario+" ¡listo!";
		    	}
				case "fue divertido":
		    		return "@"+nombreUsuario+" si!";
				case "es el":
				{
//					conteoIntentos++;
					respuesta=RF10Tests.ELEGIDO; //Linea solo utilizada para test
		    		String intento = mensaje.replaceAll("\\D", "");
		    		return "@"+nombreUsuario+" "+evaluarIntento(Integer.parseInt(intento));
		    	}
			}
		}
	    else
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		return null;
	}
	
	public String evaluarIntento (int intento) {
		if(intento<respuesta)
			return "más grande";
		else
		{
			if(intento>respuesta)
				return "más chico";
			else
//				return "¡si! Adivinaste en "+conteoIntentos+" pasos...";
				return "¡si! Adivinaste!";
		}
	}
}