package bot.handlers;


import java.util.regex.Pattern;

public abstract class AsistantSentenceHandler {

	protected AsistantSentenceHandler nextHandler;
	protected Pattern patron;
	
	public void setNextAction(AsistantSentenceHandler nextHandler){
	      this.nextHandler = nextHandler;
	}
	
	public abstract String giveAnswer(String mensaje, String nombreUsuario);
}
