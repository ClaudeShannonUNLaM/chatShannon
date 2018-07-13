package bot.handlers;


import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;

public abstract class AsistantSentenceHandler {

	protected AsistantSentenceHandler nextHandler;
	protected Pattern patron;
	
	public void setNextAction(AsistantSentenceHandler nextHandler){
	      this.nextHandler = nextHandler;
	}
	
	public abstract Mensaje giveAnswer(Mensaje mensaje);
}
