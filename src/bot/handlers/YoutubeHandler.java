package bot.handlers;

import java.io.File;
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
		Mensaje msj ;
		File f;
		if(matcher.matches()){
			f=new File("video\\nevergonnagiveyouup.mp4");
			
			msj=new Mensaje("Aqui tienes.");
			msj.setVideo(f);
			return msj;										
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
		
	}	
		
	
	

}
