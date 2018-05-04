package asistente;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TiempoActual {
	
	private final static Pattern[] pratronPreguntaSobreHoy = {Pattern.compile("^¿qué ?(hora|día) es,"), Pattern.compile("la ?(hora|fecha) por favor"), Pattern.compile("^me decís la ?(hora|fecha)") };

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
	
	public static String respuestaHoy(String mensaje, String tipo){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formato;
		switch(tipo){
			case "hora":
				formato = new SimpleDateFormat("hh:mm a");
				mensaje = "@"+TestAsistente.USUARIO+" son las "+ formato.format(c.getTime());
				break;
			case "fecha":
			case "día":
				formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
				mensaje = "@"+TestAsistente.USUARIO+" hoy es " + formato.format(c.getTime());
				break;
			case "díaSemana":
				formato = new SimpleDateFormat("EEEE", new Locale("es","ES"));
				mensaje = "@"+TestAsistente.USUARIO+" hoy es "+ formato.format(c.getTime());
				break;
		}
		return mensaje;
	}
}
