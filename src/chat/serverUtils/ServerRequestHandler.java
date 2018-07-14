package chat.serverUtils;

import bot.handlers.AsistantSentenceHandler;

public abstract class ServerRequestHandler {
	protected AsistantSentenceHandler nextHandler;
	protected FuncionalidadServerEnum requestTypeId;
	
	public void setNextAction(AsistantSentenceHandler nextHandler){
	      this.nextHandler = nextHandler;
	}
	
	public abstract String giveAnswer(ServerRequest request);
}
