package bot.handlers;


public class MostrarMemeHandler extends AsistantSentenceHandler {

	private String [] nomMemes={"bad luck brian","buddy jesus","evil kid","forever alone","fat boy","i dont know who"
			,"its trap","love is all you need","my face when","sometimes i think","take my money","what if i told you"
			,"when the","you know it","you sending"};
	
	 public MostrarMemeHandler() {

	}
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
	
	String path="img\\memes\\";	
	
	for(int i=0;i<nomMemes.length;i++)
	{
		if(mensaje.toLowerCase().contains(nomMemes[i]))
		{
			return path + nomMemes[i] +".jpg";
		}
	}
	
		return this.nextHandler.giveAnswer(mensaje, nombreUsuario);		
	
	}

}
