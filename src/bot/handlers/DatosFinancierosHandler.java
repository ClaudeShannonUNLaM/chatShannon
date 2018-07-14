package bot.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bot.datosFinancieros.ConsultaCotizacion;
import chat.serverUtils.Mensaje;

public class DatosFinancierosHandler extends AsistantSentenceHandler {
	
	public DatosFinancierosHandler() {	
		patron = Pattern.compile(".*(mostrame|quiero ver|cómo estan|Mostrame|Quiero ver|Cómo están).*(?:finanzas|datos financieros|Finanzas|Datos Financieros|Datos financieros).*");	
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje,String nombreUsuario){
		
		Mensaje msj=new Mensaje(nombreUsuario);
		Matcher matcher = patron.matcher(mensaje);
		
		String respuesta = "@" + nombreUsuario;
	    if (matcher.matches()) {
	        try {
				respuesta += resolverDatos();
				respuesta += ".";
			} catch (org.apache.http.ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        msj.setMensaje(respuesta);
	        return msj;
	    }
	    
	    return this.nextHandler.giveAnswer(mensaje,nombreUsuario);
	}
	
	private String resolverDatos() throws org.apache.http.ParseException, IOException {
		
		String consulta="";
		ArrayList <ConsultaCotizacion> consultas = new ArrayList <ConsultaCotizacion>();
		consultas.add(new ConsultaCotizacion(", Valor del Dolar: ",6,2,"http://api.estadisticasbcra.com/usd"));
		consultas.add(new ConsultaCotizacion(", Variacion anual del Dolar: ",7,2,"http://api.estadisticasbcra.com/var_usd_anual"));
		consultas.add(new ConsultaCotizacion(", Variacion de cotizacion Dolar Billete y Dolar Oficial: ",6,2,"http://api.estadisticasbcra.com/usd"));
		consultas.add(new ConsultaCotizacion(", Valor del Merval: ",7,2,"http://api.estadisticasbcra.com/merval"));
		consultas.add(new ConsultaCotizacion(", Base Monetaria: ", 9, 2, "http://api.estadisticasbcra.com/base"));
		consultas.add(new ConsultaCotizacion(", Reservas Internacionales: ", 7, 2, "http://api.estadisticasbcra.com/reservas"));
		consultas.add(new ConsultaCotizacion(", Lebac: ", 8, 2, "http://api.estadisticasbcra.com/lebac"));
		
		String token="BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjI3MTc1MzMsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJtaWd1ZV81QGxpdmUuY29tLmFyIn0.fz9Clb5HKUQJx_V7lPqXaQMdTeWYIXolXUaCoJDf7KTEQOZGAdrXFtVy8300XEyOiOuz5OqWavOr-_mgwailjw"; 
		
		for(int index=0;index<consultas.size();index++) {
			
			HttpClient httpclient= HttpClientBuilder.create().build();  // the http-client, that will send the request
			HttpGet httpGet = new HttpGet(consultas.get(index).getApiWeb());
	        httpGet.addHeader("Authorization", token);
	        HttpResponse response = httpclient.execute(httpGet); // the client executes the request and gets a response      
	        int responseCode = response.getStatusLine().getStatusCode();  // check the response code
			
			switch (responseCode) {
		    	case 200: { 
		    		// todo salio bien y esta conectado
		    		String stringResponse = EntityUtils.toString(response.getEntity()); 
		    		consulta += consultas.get(index).getRespuesta() +(stringResponse.substring(stringResponse.length()-consultas.get(index).getIndiceMenor(), stringResponse.length()-consultas.get(index).getIndiceMayor()));
		    		break;
		        }
		        case 500: {
		        	consulta += "Problemas Con El Servidor de: " + consultas.get(index).getApiWeb();
		            // server problems
		            break;
		        }
		        case 403: {
		        	consulta += "Error en la autorizacion para acceder a la API: " + consultas.get(index).getApiWeb() +" ";
		            // error de autorizacion de acceso 
		           	break;
		        }
			}
		}		
		return consulta;
	}
}
