package handlers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TiempoActualHandler extends AsistantSentenceHandler {
	
	private final static Pattern[] pratronPreguntaSobreHoy = {Pattern.compile("^¿qué ?(hora|día) es,"), Pattern.compile("la ?(hora|fecha) por favor"), Pattern.compile("^me decís la ?(hora|fecha)") };
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		
		String rHoy = preguntaPorHoy(mensaje);			
					
		if(!rHoy.isEmpty())
			return  respuestaHoy(mensaje, rHoy, nombreUsuario);
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);	
	}	

	public static String preguntaPorHoy(String mensaje){
		if(mensaje.contains("¿qué día de la semana es hoy"))
			return "díaSemana";

		for(int i = 0; i < pratronPreguntaSobreHoy.length; i++){
			Matcher x = pratronPreguntaSobreHoy[i].matcher(mensaje);
			if(x.find())
				return x.group(1);
		}
		
		return "";
	}
	
	private String respuestaHoy(String mensaje, String tipo, String nombreUsuario){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formato;
		switch(tipo){
			case "hora":
				formato = new SimpleDateFormat("hh:mm a");
				mensaje = "@"+ nombreUsuario +" son las "+ formato.format(c.getTime());
				break;
			case "fecha":
			case "día":
				formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
				mensaje = "@"+ nombreUsuario +" hoy es " + formato.format(c.getTime());
				break;
			case "díaSemana":
				formato = new SimpleDateFormat("EEEE", new Locale("es","ES"));
				mensaje = "@"+ nombreUsuario +" hoy es "+ formato.format(c.getTime());
				break;
		}
		return mensaje;
	}

}
