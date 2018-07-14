package bot.handlers;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;
import tests.RF10Tests;

public class MayorMenorAdivinadoHandler  extends AsistantSentenceHandler{
	private int respuesta;
//	private int conteoIntentos;

	public MayorMenorAdivinadoHandler () {
		patron = Pattern.compile(".*(del 1 al 100|es el|fue divertido).*");
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		Mensaje msj;
		if (matcher.matches()) {

			switch (matcher.group(1)) { 
				case "del 1 al 100" :
				{

					Random rnd = new Random();
					this.respuesta = (int)(rnd.nextDouble() * 100 + 0);
					msj=new Mensaje("@"+nombreUsuario+" ¡listo!");
		    		return msj;
		    	}
				case "fue divertido":
					msj=new Mensaje("@"+nombreUsuario+" si!");
					return msj;
				case "es el":
				{	
					respuesta=RF10Tests.ELEGIDO; //Linea solo utilizada para test
		    		String intento = mensaje.replaceAll("\\D", "");
		    		msj=new Mensaje("@"+nombreUsuario+" "+evaluarIntento(Integer.parseInt(intento)));
		    		return msj;
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
//				return "�si! Adivinaste en "+conteoIntentos+" pasos...";
				return "¡si! Adivinaste!";
		}
	}
}