package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import chat.serverUtils.Mensaje;

public class YoutubeHandler extends AsistantSentenceHandler{

	public YoutubeHandler() {
		patron = Pattern.compile(".*(mostrame|quiero ver).*(?:videos|Video|videos|Videos|Videos|video|youtube|Youtube).*");
		
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		Mensaje msj=new Mensaje ();
		if(matcher.matches()){
			msj.setDescripcion("Aqui tienes.");
			return msj;										
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		
	}	
		
	
	

}
