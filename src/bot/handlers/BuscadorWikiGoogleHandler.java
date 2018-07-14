package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import org.json.JSONException;

import bot.buscadorWikiGoogle.BuscadorWikiGoogle;
import chat.serverUtils.Mensaje;

public class BuscadorWikiGoogleHandler extends AsistantSentenceHandler{
	
	private final static Pattern[] pratronPreguntaSobreHoy  = {Pattern.compile("buscar en wikipedia (.+)"),Pattern.compile("buscame en wikipedia (.+)"),Pattern.compile("busca en wikipedia (.+)")};
	private Mensaje objetoMensaje;
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		String busqueda = obtenerCadenaDeBusqueda(mensaje.trim());		
		objetoMensaje=new Mensaje();
		if(!busqueda.isEmpty())
		{
			BuscadorWikiGoogle bwg = new BuscadorWikiGoogle();
			try {
				objetoMensaje= bwg.buscar(busqueda) ;
			} catch (IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return objetoMensaje;
		}
			
	
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	public static String obtenerCadenaDeBusqueda(String mensaje){
		
		for(int i = 0; i < pratronPreguntaSobreHoy.length; i++){
			Matcher x = pratronPreguntaSobreHoy[i].matcher(mensaje);
			if(x.find())
				return x.group(1);
		}
		
		return "";
	}
} 