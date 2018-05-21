package handlers;

import java.util.Calendar;
import java.util.StringTokenizer;

import fecha.Fecha;

public class DiferenciaFechasHandler extends AsistantSentenceHandler{
		
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {		
		if(true){	//(mensaje cumple con expresion regular)			
			/*
			if() (mensaje cumple con que esta pidiendo con averiguar cuantos dias faltan)
				return calcular(mensaje,nombreUsuario ,false);				
		
			else if()(mensaje cumple con que esta pidiendo con averiguar cuantos dias pasaron)
				return calcular(mensaje,nombreUsuario, true);
			
			else if(){(mensaje cumple con que esta pidiendo diferencia de fechas)
				Calendar fechaPreguntada = Fecha.cadenaAFecha(mensaje);				
				long diasDiferencia = Fecha.restarFechas(Fecha.getToday(), fechaPreguntada);
				return diasPasaron(diasDiferencia,Fecha.getToday(),fechaPreguntada, nombreUsuario);
			}			
			else if(){
				Calendar fechaRequerida;
				if(mensaje.contains("mundial")){
					fechaRequerida = Calendar.getInstance();
				}else
					fechaRequerida = Fecha.cadenaAFecha(mensaje);
				
				long diasRestantes = Fecha.restarFechas(fechaRequerida,Fecha.getToday());
				return diasFaltan(diasRestantes,nombreUsuario);				
			}
			*/
			return null; // Esta linea de codigo se debe sacar. Se deja para que no tire error en esta primera etapa.
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);	
		
	}
	
	
	private String calcular(String mensaje, String nombreUsuario, boolean averiguarDiasPasados){		
			
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
		Calendar fecha = Calendar.getInstance();
		
		if (caso=="mes"){
			fecha.add(Calendar.MONTH,aSumar);
		}
		else if(caso=="año"){
			fecha.add(Calendar.YEAR,aSumar);
		}
		else
			fecha.add(Calendar.DAY_OF_MONTH,aSumar);
		
		return fecha;
	}	
	
	private String diasPasaron(long dias,Calendar f1,Calendar f2, String nombreUsuario){
		return "@" + nombreUsuario + " entre el "+ Fecha.fechaACadenaSinDia(f2) 
				+ " y el " + Fecha.fechaACadenaSinDia(f1) + " pasaron " + (dias) + " días";
	}	
	
	private String diasFaltan(long dias, String nombreUsuario){
		return "@" + nombreUsuario +" faltan "+ dias +" días";
	}
	
	private String fechaHace(Calendar f1, String nombreUsuario){		
		return "@" + nombreUsuario + " fue el "+ Fecha.fechaACadena(f1);
	}
	
	private String fechaDentro(Calendar f1, String nombreUsuario){		
		return "@" + nombreUsuario + " será el "+ Fecha.fechaACadena(f1);
	}
}
