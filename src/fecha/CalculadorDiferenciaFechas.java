package fecha;

import java.util.Calendar;
import java.util.StringTokenizer;

import asistente.TestAsistente;

public class CalculadorDiferenciaFechas {
	
	public static String calcular(String mensaje, boolean averiguarDiasPasados){		
		
		StringTokenizer cadenaCompleta = new StringTokenizer(mensaje);
		String partes,periodo;
	    int diferencia=0;
	    Calendar resultado;	    
		
	    while (cadenaCompleta.hasMoreTokens()) {
	         partes=cadenaCompleta.nextToken();
	         if(partes.matches("^[1-9][0-9]*$")){
	        	 diferencia=Integer.parseInt(partes);
	         }
	     }
	     if(mensaje.contains("d�as")||mensaje.contains("d�a")){
	    	 periodo="d�a";
	     }
	     if(averiguarDiasPasados){
	    	 if(mensaje.contains("ayer")){
		    	 periodo="d�a";
		    	 diferencia=1;
		     }
	     }	     
	     if(mensaje.contains("meses")||mensaje.contains("mes")){
	    	 periodo="mes";
	     }
	     else{
	    	 periodo="a�o";
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
		else if(caso=="a�o"){
			fecha.add(Calendar.YEAR,aSumar);
		}
		else
			fecha.add(Calendar.DAY_OF_MONTH,aSumar);
		
		return fecha;
	}	
	
	public static String diasPasaron(long dias,Calendar f1,Calendar f2){
		return "@" +TestAsistente.USUARIO + " entre el "+ Fecha.fechaACadena(f2) 
				+" y el " + Fecha.fechaACadena(f1)+" pasaron " + (dias-1) + " d�as";
	}	
	
	public static String diasFaltan(long dias,Calendar f1,Calendar f2){
		return "@" +TestAsistente.USUARIO +" faltan "+ dias +" d�as";
	}
	
	private static String fechaHace(Calendar f1){		
		return "@" +TestAsistente.USUARIO + " fue el "+ Fecha.fechaACadena(f1);
	}
	
	private static String fechaDentro(Calendar f1){		
		return "@" +TestAsistente.USUARIO + " ser� el "+Fecha.fechaACadena(f1);
	}
		
	
	
}
