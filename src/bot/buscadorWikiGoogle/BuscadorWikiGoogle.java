package bot.buscadorWikiGoogle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import chat.serverUtils.Mensaje;
import tests.TestAsistente;

public class BuscadorWikiGoogle  {
		
	private final static String wikipediaAPI="https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
	private final static String googleAPI="https://www.googleapis.com/customsearch/v1?key=AIzaSyBpZxeSR-UtqGXu4mP4JQONwmpZ8UWTEgc&cx=010014002917633397902:9xiswcj3azq&fields=items(snippet,link)&lr=lang_es&q=";
	private final static String wikipedia="https://en.wikipedia.org/wiki/";
//
	
		
	public Mensaje buscar(String msj) throws IOException, JSONException
	{	
		Mensaje mensaje=new Mensaje(null,null,msj);
		
		URL url = new URL(wikipediaAPI + convertirAFormatoWikipedia(msj));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();	
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		
		while ((output = br.readLine()) != null) {
			
		
			
			 
			if(output.contains("extract:"))
			{
				if(output.substring(output.indexOf("extract:")+8,output.length()).contains("may refer to:"))
					mensaje=buscarEnGoogle(msj);
				else
				{
					mensaje.setLink(wikipedia+convertirAFormatoWikipedia(msj));
					mensaje.setDescripcion(output.substring(output.indexOf("extract:")+8,output.length()));
				}
			}	
			else
				mensaje=buscarEnGoogle(msj);
			
			
		}

		conn.disconnect();
				
		return mensaje;

	}
	
	private String convertirAFormatoWikipedia(String mensaje)
	{
		String cadenaADevolver="";
		int contadorLetras=0;
		
		char[] aux=mensaje.replace(' ', '_').toCharArray();
		for(int i=0 ; i<mensaje.length();i++)
		{	
			if(contadorLetras==0)
			{	cadenaADevolver=cadenaADevolver+Character.toUpperCase(aux[i]) ;
			 	contadorLetras++;
			}
			else
			{
				if(aux[i]=='_')    	
					contadorLetras=0;
			    else
			    	contadorLetras++;
			    	
				cadenaADevolver=cadenaADevolver+aux[i];
			}
		}
		
		return cadenaADevolver; 
	}
	
	
	private Mensaje buscarEnGoogle(String msj) throws IOException
	{
		Mensaje mensaje=new Mensaje(null,null,msj);
		
		URL url = new URL(googleAPI + msj);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();	
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		
		while ((output = br.readLine()) != null)
		{
			mensaje.setLink(output.substring(output.indexOf("link: ")+7,output.indexOf(",")-1));
			mensaje.setDescripcion(	output.substring(output.indexOf("snippet: ")+10,output.indexOf("}")-2));
		}
		conn.disconnect();
		return mensaje;
	}

}


		
		//si en la api de wikipedia dentro del campo extract contains (mensaje)+"may refer to:" entonces tengo que buscar en google por desambiguacion. 
		//armado de link https://en.wikipedia.org/wiki/ + convertirAFormatoWikipedia(msj)
				
		//si la etiqueta pages tiene dentro un -1 no encontro resultado //en wikipedia .
		//title es lo que tengo que poner delante de https://es.wikipedia.org/wiki/ para que devuelva el link.
		//api key 9/7/2018 :AIzaSyBpZxeSR-UtqGXu4mP4JQONwmpZ8UWTEgc 
		//https://www.googleapis.com/customsearch/v1?key=AIzaSyBpZxeSR-UtqGXu4mP4JQONwmpZ8UWTEgc&cx=017576662512468239146:omuauf_lfve&fields=items(title,snippet,link,pagemap/cse_thumbnail/src)&q=cars&lr=lang_es			
		//buscardor propio
		//https://cse.google.es/cse/publicurl?cx=010014002917633397902:9xiswcj3azq
