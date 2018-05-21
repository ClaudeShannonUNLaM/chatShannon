package handlers;

public class DefaultHandler extends AsistantSentenceHandler {

	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		return "Disculpa... no entiendo el pedido, @"+ nombreUsuario +" ¿podrías repetirlo?";
	}

}
