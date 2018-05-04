package fecha;

import java.util.Calendar;
import java.util.StringTokenizer;

import asistente.TestAsistente;

public class CalculadorDiferenciaFechas {
	
	public static String calcular(String mensaje, boolean averiguarDiasPasados){		
		
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
		     return fechaHace(resultado);	 
	     }
	     else{
	    	 resultado= diaDentro(diferencia, periodo);
		     return fechaDentro(resultado);	 
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
	
	public static String diasPasaron(long dias,Calendar f1,Calendar f2){
		return "@" +TestAsistente.USUARIO + " entre el "+ Fecha.fechaACadenaSinDia(f2) 
				+" y el " + Fecha.fechaACadenaSinDia(f1)+" pasaron " + (dias) + " días";
	}	
	
	public static String diasFaltan(long dias){
		return "@" +TestAsistente.USUARIO +" faltan "+ dias +" días";
	}
	
	private static String fechaHace(Calendar f1){		
		return "@" +TestAsistente.USUARIO + " fue el "+ Fecha.fechaACadena(f1);
	}
	
	private static String fechaDentro(Calendar f1){		
		return "@" +TestAsistente.USUARIO + " será el "+Fecha.fechaACadena(f1);
	}
		
	
	
}
