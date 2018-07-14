package bot.handlers;

import chat.serverUtils.Mensaje;

public class DefaultHandler extends AsistantSentenceHandler {

	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Mensaje msj=new Mensaje();
		msj.setDescripcion("Disculpa... no entiendo el pedido, @"+ nombreUsuario +" ¿podrías repetirlo?");
		return msj;
	}

}
