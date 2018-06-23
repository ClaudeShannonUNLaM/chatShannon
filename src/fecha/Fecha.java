package fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Fecha {	
	
		
	public static Calendar getToday() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		return today;
	}
	
	public static long restarFechas(Calendar f1, Calendar f2){		
		long diff = f1.getTime().getTime() - f2.getTime().getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);		
	}
	
	public static Calendar cadenaAFecha(String f1) throws ParseException{
		if(!f1.contains("(\\d{4})"))
			return cadenaAFechaSinAño(f1);
		
		else{
			String[]cadenaCompleta=f1.split("el");
			String fechaConSigno = cadenaCompleta[1];
			String[]fechaSinSigno=fechaConSigno.split("\\?");
			String fechaOk = fechaSinSigno[0];
			Calendar fechaRetorno = Calendar.getInstance();
			SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
			fechaRetorno.setTime(formato.parse(fechaOk));
			
			return fechaRetorno;
		}
	}
	
	private static Calendar cadenaAFechaSinAño(String f1) throws ParseException{

		String[]cadenaCompleta=f1.split("el");
		String fechaConSigno = cadenaCompleta[1];
		String[]fechaSinSigno=fechaConSigno.split("\\?");
		String fechaOk = fechaSinSigno[0];
		fechaOk+=" de 2018";
		Calendar fechaRetorno = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		fechaRetorno.setTime(formato.parse(fechaOk));
		return fechaRetorno;
	}
	
	public static String fechaACadena(Calendar f1){		
		Date d1=f1.getTime();
		SimpleDateFormat formato = 
			    new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
			return formato.format(d1);
	}
	public static String fechaACadenaSinDia(Calendar f1){		
		Date d1=f1.getTime();
		SimpleDateFormat formato = 
			    new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
			return formato.format(d1);
	}
	
}
