package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;
import hibernate.usuario.*;

import hibernate.rss.*;
public class AgregarRSSHandler extends AsistantSentenceHandler{
	

	public AgregarRSSHandler(){
		patron = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)");
	}
//	public static void main (String []args){
//		AgregarRSSHandler a=new AgregarRSSHandler();
//		Matcher matcher = a.patron.matcher("hola https://www.google.com");		
//	    if (matcher.find()) {	    	
//	    	System.out.println(matcher.group(0));
//	    }else System.out.println("a");
//	}
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);
		Mensaje msj=new Mensaje(mensaje);
	    if (matcher.find()) {
	    	RssCreation r = new RssCreation();
	    	UsuarioController u = new UsuarioController();
	    	r.insertar(matcher.group(0), u.BuscarUsuario(nombreUsuario).getId());
	    	//guardar en la base de datos  para tal usuario
	    	msj.setMensaje("@" + nombreUsuario + ", he registrado tu RSS: " + matcher.group(0));
	    	return msj;
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}	
	public static void main(String[] args) {
	//http://worldscreen.com/tvdrama/feed/   http://worldscreen.com/tvlatina/feed/  http://rss.cnn.com/rss/edition.rss
	AgregarRSSHandler r  = new AgregarRSSHandler();
	System.out.println(r.giveAnswer("quiero guardar: http://www.bbc.co.uk/mundo/ultimas_noticias/index.xml", "marinolautaro").getMensaje());
    //worldscreen.com/tvdrama/feed/"));
	//https://www.pagina12.com.ar/diario/rss/ultimas_noticias.xml
}

}