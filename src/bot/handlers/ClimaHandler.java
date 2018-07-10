package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClimaHandler extends AsistantSentenceHandler{
	

	public ClimaHandler(){
		patron = Pattern.compile(".*(clima|soleado|lluvia|llover|paraguas|pronóstico)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.find()) {	    	
	    	return "@" + nombreUsuario + ", hoy el clima está " + responderClima();
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}
//	public static void main(String [] args){
//		ClimaHandler c = new ClimaHandler();
//		System.out.println(c.giveAnswer("clima pronostico", "aa"));
//	}
	private static String responderClima() {
		 try {

				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=3435910&APPID=b5e3387905861d71e52851e398db3f9f");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				String clima="" ;
				//System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					//System.out.println(output);
					JSONObject obj = new JSONObject(output);
					// String pageName = obj.getJSONObject("pageInfo").getString("pageName");
					
					//JSONArray arr = obj.getJSONArray("{}");
					//for (int i = 0; i < arr.length(); i++)
					//{
					clima= obj.getString("weather");
					//System.out.println(clima);
					//}
				//	return clima.split("main")[1];
				}

						conn.disconnect();
						String retorno= clima.split("main")[1];
						if(retorno.contains("Clouds"))return "nublado.";
						else if (retorno.contains(""))return "soleado.";
						else return "lluvioso.";

			  } catch (MalformedURLException e) {

				e.printStackTrace();
				return "Error en la api del clima";
			  } catch (IOException e) {

				e.printStackTrace();
				return "Error en la api del clima";
			  } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Error en la api del clima";
			  }
		
	}
}
