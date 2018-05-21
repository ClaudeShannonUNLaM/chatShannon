package handlers;

public abstract class AsistantSentenceHandler {

	protected AsistantSentenceHandler nextHandler;
	
	public void setNextAction(AsistantSentenceHandler nextHandler){
	      this.nextHandler = nextHandler;
	}
	
	public abstract String giveAnswer(String mensaje, String nombreUsuario);
}
