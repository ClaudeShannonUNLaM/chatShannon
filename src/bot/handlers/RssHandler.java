package bot.handlers;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.serverUtils.Mensaje;
import hibernate.usuario.*;
import hibernate.rss.*;

public class RssHandler extends AsistantSentenceHandler {

	private int idRss;
	private String URL;
	private String [] URLS;	
		public RssHandler(int idRss, String uRL, String[] uRLS) {
		super();
		this.idRss = idRss;
		URL = uRL;
		URLS = uRLS;
	}


		//faltaria agregar rss a la base de datos
		@Override
		public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
			Mensaje msj = new Mensaje (mensaje);
			getURLSfromBDD();
			String concatenacion ="";
			Matcher matcher = patron.matcher(mensaje);		
		    if (matcher.find()) {
		    	RssCreation r = new RssCreation();
		    	UsuarioController u = new UsuarioController();
		    	URLS = r.devolverListaURLS(u.BuscarUsuario(nombreUsuario).getId());
		    	
		    	for(String S : URLS)
		    		
		    		concatenacion+=readRSSFeed(S);
					msj.setDescripcion("Estas son las principales noticias, @" + nombreUsuario + " : " + concatenacion.replace("[", "").replace("CDATA", "").replace("]","").replace("<","").replace(">","").replace("!", "-"));		    		return msj;
		    } else 
		    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
		}		
	
private void getURLSfromBDD(){
	//aca agrego todas las URLS a mi lista
}

	public static void main(String[] args) {
    	//http://worldscreen.com/tvdrama/feed/   http://worldscreen.com/tvlatina/feed/  http://rss.cnn.com/rss/edition.rss
		RssHandler r  = new RssHandler();
		System.out.println(r.giveAnswer("dame rss", "marinolautaro").getDescripcion());
		//System.out.println(r.giveAnswer("dame rss", "marinolautaro").getDescripcion().split("\n")[1]);
        //worldscreen.com/tvdrama/feed/"));
    }
    public static String readRSSFeed(String urlAddress){
        try{
            URL rssUrl = new URL (urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                int titleEndIndex = 0;
                int titleStartIndex = 0;
                while (titleStartIndex >= 0) {
                    titleStartIndex = line.indexOf("<title>", titleEndIndex);
                    if (titleStartIndex >= 0) {
                        titleEndIndex = line.indexOf("</title>", titleStartIndex);
                        sourceCode += line.substring(titleStartIndex + "<title>".length(), titleEndIndex) + "\n";
                    }
                }
            }
            in.close();
//            String retorno = sourceCode.replaceAll(".*(<![CDATA[)", "");
//            return retorno.split(".*(]]>)")[0];
        return sourceCode;
        } catch (MalformedURLException ue){
           // System.out.println("Malformed URL");
        } catch (IOException ioe){
           // System.out.println("Something went wrong reading the contents");
        }
        return "";
    }
	public int getIdRss() {
		return idRss;
	}
	public void setIdRss(int idRss) {
		this.idRss = idRss;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
		public String[] getURLS() {
		return URLS;
	}


	public void setURLS(String[] uRLS) {
		URLS = uRLS;
	}


		public RssHandler(){
			patron = Pattern.compile(".*(rss|blog)");
		}
}
