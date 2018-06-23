package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class YoutubeHandler extends AsistantSentenceHandler{

	public YoutubeHandler() {
		patron = Pattern.compile(".*(mostrame|quiero ver).*(?:videos|Video|videos|Videos|Videos|video|youtube|Youtube).*");
		
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
		if(matcher.matches()){			
			return "Aqui tienes.";										
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		
	}	
		
	
	

}
