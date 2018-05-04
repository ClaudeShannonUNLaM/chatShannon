package asistente;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TiempoActual {
	
	private final static Pattern[] pratronPreguntaSobreHoy = {Pattern.compile("^�qu� ?(hora|d�a) es,"), Pattern.compile("la ?(hora|fecha) por favor"), Pattern.compile("^me dec�s la ?(hora|fecha)") };

	public static String preguntaPorHoy(String mensaje){
		if(mensaje.contains("�qu� d�a de la semana es hoy"))
			return "d�aSemana";

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
			case "d�a":
				formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
				mensaje = "@"+TestAsistente.USUARIO+" hoy es " + formato.format(c.getTime());
				break;
			case "d�aSemana":
				formato = new SimpleDateFormat("EEEE", new Locale("es","ES"));
				mensaje = "@"+TestAsistente.USUARIO+" hoy es "+ formato.format(c.getTime());
				break;
		}
		return mensaje;
	}
}
