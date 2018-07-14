package bot.handlers;

import java.io.File;

import chat.serverUtils.Mensaje;

public class MostrarMemeHandler extends AsistantSentenceHandler {

	private String [] nomMemes={"bad luck brian","buddy jesus","evil kid","forever alone","fat boy","i dont know who"
			,"its trap","love is all you need","my face when","sometimes i think","take my money","what if i told you"
			,"when the","you know it","you sending"};
	
	 public MostrarMemeHandler() {

	}
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
	
	File img;	
	Mensaje msj=new Mensaje();
	for(int i=0;i<nomMemes.length;i++)
	{
		if(mensaje.toLowerCase().contains(nomMemes[i]))
		{
			img=new File("img\\memes\\" + nomMemes[i] +".jpg");
			msj.setDescripcion("MEME: "+ nomMemes[i]);
			msj.setImagen(img);
			return msj;
		}
	}
	
		return this.nextHandler.giveAnswer(mensaje, nombreUsuario);		
	
	}

}
