package bot.buscadorWikiGoogle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
<<<<<<< HEAD

import java.net.URISyntaxException;
=======
import java.net.MalformedURLException;
>>>>>>> a4bbfbfbf898b6e3cbfb947b56abd8702796154a
import java.net.URL;

import java.net.URLEncoder;

import org.json.JSONException;
import chat.serverUtils.Mensaje;


public class BuscadorWikiGoogle  {
		
	private final static String wikipediaAPI="https://en.wikipedia.org/w/api.php?";
	private final static String googleAPI="https://www.googleapis.com/customsearch/v1?";
	private final static String wikipedia="https://en.wikipedia.org/wiki/";
//
	
		
	public Mensaje buscar(String msj) throws IOException, JSONException, URISyntaxException
	{	
		Mensaje mensaje;
		
		String parametros="format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
		String charset = "UTF-8"; 
		String wAPI=URLEncoder.encode(parametros,charset);
		HttpURLConnection conn = (HttpURLConnection) new URL( wikipediaAPI+wAPI+convertirAFormatoWikipedia(msj)) .openConnection();	
	
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		
		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
		 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String output;
		
		while ((output = br.readLine()) != null)
		{
			
			if(output.contains("extract:"))
			{
				if(output.substring(output.indexOf("extract:")+8,output.length()).contains("may refer to:"))
					return buscarEnGoogle(msj);
				else
				{
					mensaje=new Mensaje(output.substring(output.indexOf("extract:")+8,output.length()));
					
					mensaje.setLink(wikipedia+convertirAFormatoWikipedia(msj));
				//	conn.disconnect();
					return mensaje;
				}
				
			}else
				return buscarEnGoogle(msj +" wikipedia");
			
		}
		return null;
		
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
		Mensaje mensaje;
		String parametros="key=AIzaSyBpZxeSR-UtqGXu4mP4JQONwmpZ8UWTEgc&cx=010014002917633397902:9xiswcj3azq&fields=items(snippet,link)&lr=lang_es&q=";
		String charset = "UTF-8"; 
		String wAPI=URLEncoder.encode(msj,charset);
		
		HttpURLConnection conn = (HttpURLConnection) new URL(googleAPI+parametros+ wAPI).openConnection();	
		
		conn.setRequestMethod("GET");
<<<<<<< HEAD
		conn.setRequestProperty("Content-Type", "application/json");
		
=======
		conn.setRequestProperty("Accept", "application/json");

>>>>>>> a4bbfbfbf898b6e3cbfb947b56abd8702796154a
		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		mensaje=new Mensaje("");
		int cant =0;
		output = br.readLine();
		while (cant < 7)
		{
			if(output.contains("link"))
			{
				
				mensaje.setLink(output.substring(output.indexOf("link")+8,output.indexOf(",")-1));
			}
			else
				if(output.contains("snippet"))
					mensaje.setMensaje(output.substring(output.indexOf("snippet")+11,output.length()-1));
			output = br.readLine();
			cant++;
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
