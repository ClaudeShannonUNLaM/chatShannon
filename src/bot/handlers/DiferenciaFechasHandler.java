package bot.handlers;

import java.sql.Date;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.fecha.Fecha;
import chat.serverUtils.Mensaje;

public class DiferenciaFechasHandler extends AsistantSentenceHandler{
	

	public DiferenciaFechasHandler () {
		patron = Pattern.compile(".*(qué día será dentro de|qué día fue|cuántos días pasaron desde el|cuántos días faltan para el).*");	
	}
	
	@Override
	public Mensaje giveAnswer(String mensaje, String nombreUsuario) {
		Calendar fechaCalculo= Calendar.getInstance();
		fechaCalculo.set(Calendar.YEAR,2018);
		fechaCalculo.set(Calendar.MONTH,4);
		fechaCalculo.set(Calendar.DAY_OF_MONTH,31);
		Matcher matcher = patron.matcher(mensaje);		
	    if(matcher.find()) {								
			if(mensaje.contains("qué día será dentro de"))
				return calcular(mensaje,nombreUsuario ,false);	
			else if(mensaje.contains("qué día fue"))
				return calcular(mensaje,nombreUsuario, true);
			
			else if(mensaje.contains("cuántos días pasaron desde el")){
					try{
						Calendar fechaPreguntada = Fecha.cadenaAFecha(mensaje);				
						long diasDiferencia = Fecha.restarFechas(fechaCalculo, fechaPreguntada);
						return diasPasaron(diasDiferencia,fechaCalculo,fechaPreguntada, nombreUsuario);
					}
					 catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
			}
			else if(mensaje.contains("cuántos días faltan para el")){
				try{
					Calendar fechaPreguntada = Fecha.cadenaAFecha(mensaje);				
					long diasDiferencia = (-1)* Fecha.restarFechas(fechaCalculo, fechaPreguntada)+1;
					return diasFaltan(diasDiferencia, nombreUsuario);
				}
				 catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			return null;
	    }
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	
	private Mensaje calcular(String mensaje, String nombreUsuario, boolean averiguarDiasPasados){		
			
			StringTokenizer cadenaCompleta = new StringTokenizer(mensaje);
			String partes,periodo = "";
		    int diferencia=0;
		    Calendar resultado;	    
			
		    while (cadenaCompleta.hasMoreTokens()) {
		         partes=cadenaCompleta.nextToken();
		         if(partes.matches("^[1-9][0-9]*$")){
		        	 diferencia=Integer.parseInt(partes);
		         }
		     }
		     if(mensaje.contains("días")){
		    	 periodo="día";
		     }
		     
		     else if(mensaje.contains("ayer")){
		    	 periodo="día";
		    	 diferencia=1;
		     }
		     	     
		     else if(mensaje.contains("meses")){
		    	 periodo="mes";
		     }
		     else{
		    	 periodo="año";
		     }
		     
		     if(averiguarDiasPasados){
		    	 resultado= diaDentro(diferencia * (-1), periodo);
			     return fechaHace(resultado,nombreUsuario);	 
		     }
		     else{
		    	 resultado= diaDentro(diferencia, periodo);
			     return fechaDentro(resultado,nombreUsuario);	 
		     }				
		}
				
	private static Calendar diaDentro(int aSumar, String caso){
		Calendar fechaCalculo= Calendar.getInstance();
		fechaCalculo.set(Calendar.YEAR,2018);
		fechaCalculo.set(Calendar.MONTH,4);
		fechaCalculo.set(Calendar.DAY_OF_MONTH,31);
		
		if (caso=="mes"){
			fechaCalculo.add(Calendar.MONTH,aSumar);
		}
		else if(caso=="año"){
			fechaCalculo.add(Calendar.YEAR,aSumar);
		}
		else
			fechaCalculo.add(Calendar.DAY_OF_MONTH,aSumar);
		
		return fechaCalculo;
	}	
	
	private Mensaje diasPasaron(long dias,Calendar f1,Calendar f2, String nombreUsuario){
		Mensaje msj=new Mensaje();		
		msj.setDescripcion("@" + nombreUsuario + " entre el "+ Fecha.fechaACadenaSinDia(f2) 
				+ " y el " + Fecha.fechaACadenaSinDia(f1) + " pasaron " + (dias) + " días");
		return msj;
	}	
	
	private Mensaje diasFaltan(long dias, String nombreUsuario){
		Mensaje msj=new Mensaje();		
		msj.setDescripcion("@" + nombreUsuario +" faltan "+ dias +" días");
		return msj;
	}
	
	private Mensaje fechaHace(Calendar f1, String nombreUsuario){		
		Mensaje msj=new Mensaje();		
		msj.setDescripcion("@" + nombreUsuario + " fue el "+ Fecha.fechaACadena(f1));
		return msj;
	}
	
	private Mensaje fechaDentro(Calendar f1, String nombreUsuario){		
		Mensaje msj=new Mensaje();		
		msj.setDescripcion("@" + nombreUsuario + " será el "+ Fecha.fechaACadena(f1));
		return msj;
	}
}
