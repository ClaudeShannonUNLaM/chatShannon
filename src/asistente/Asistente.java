package asistente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import fecha.Fecha;

public class Asistente {
	
	private String nombre;
	
	private final String[] palabrasClavesHola = {"Hola", "buen día", " buenas tardes", "hey"}; 
	private final String[] palabrasClaveFechaDentro = {"qu� d�a ser� dentro de","qu� d�a ser� en","qu� d�a ser� ma�ana"}; 
	private final String[] palabrasClaveFechaHace ={"qu� d�a fue hace","qu� d�a fue ayer"};
	private final String[] palabrasClaveDiasPasaron ={"cu�ntos d�as pasaron desde el"};
	private final String[] palabrasClaveDiasFaltan ={"cu�ntos d�as faltan para el"};
	Asistente(){
		nombre = "Shannon";
	}
	Asistente(String nombre){
		this.nombre = nombre; 
	}
	
	public String escuchar(String mensaje) throws ParseException{
		if(!mensaje.contains("@"+nombre))
			return "";
		
		int accion = encontrarPalabraClave(mensaje);
		StringTokenizer cadenaCompleta = new StringTokenizer(mensaje);
	    String partes,tipo="";
	    int operando=0;
	    Fecha f1 = new Fecha();
		Fecha f2=new Fecha();
	    Calendar resultado;
	    long resultadoResta ;
	    
		switch (accion) {
		case 0:
			mensaje = hola();
			break;

			case 1:

			     while (cadenaCompleta.hasMoreTokens()) {
			         partes=cadenaCompleta.nextToken();
			         if(partes.matches("^[1-9][0-9]*$")){
			        	 operando=Integer.parseInt(partes);
			         }
			     }

			     if(mensaje.contains("d�as")||mensaje.contains("d�a")){
			    	 tipo="d�a";
			     }
			     if(mensaje.contains("meses")||mensaje.contains("mes")){
			    	 tipo="mes";
			     }
			     if(mensaje.contains("a�os")||mensaje.contains("a�o")){
			    	 tipo="a�o";
			     }
			     resultado=f1.diaDentro(operando, tipo);
			     mensaje = fechaDentro(resultado);
				break;
				
			case 2:
			     while (cadenaCompleta.hasMoreTokens()) {
			         partes=cadenaCompleta.nextToken();
			         if(partes.matches("^[1-9][0-9]*$")){
			        	 operando=Integer.parseInt(partes);
			        	 //operando=-operando;
			         }
			     }
			     if(mensaje.contains("d�as")||mensaje.contains("d�a")){
			    	 tipo="d�a";
			     }
			     if(mensaje.contains("ayer")){
			    	 tipo="d�a";
			    	 operando=1;
			     }
			     if(mensaje.contains("meses")||mensaje.contains("mes")){
			    	 tipo="mes";
			     }
			     if(mensaje.contains("a�os")||mensaje.contains("a�o")){
			    	 tipo="a�o";
			     }
			     resultado=f1.diaHace(operando, tipo);
			     mensaje = fechaHace(resultado);
				break;
			
			case 3:
				f2.setHoy(cadenaAFecha(mensaje));
				resultadoResta = f2.restarFechas(f1.getHoy(), "");
				mensaje = diasPasaron(resultadoResta,f1.getHoy(),f2.getHoy());
				break;
				
			case 4:
				if(mensaje.contains("mundial")){
					f2.setHoy(TestAsistente.FECHA_MUNDIAL);
				}else
				f2.setHoy(cadenaAFecha(mensaje));
				resultadoResta = f2.restarFechas(f1.getHoy(), "");
				mensaje = diasFaltan(resultadoResta,f1.getHoy(),f2.getHoy());
				break;
				
		default:
			mensaje = "Disculpa... no entiendo el pedido, @"+TestAsistente.USUARIO +" �podr�as repetirlo?";
			break;
		}

		return mensaje;
	}
	
	private int encontrarPalabraClave(String mensaje){
		for(String x: palabrasClavesHola){
			if(mensaje.contains(x)){
				return 0;
			}
		}
		for(String y: palabrasClaveFechaDentro){
			if(mensaje.contains(y)){
				return 1;
			}
		}
		for(String z: palabrasClaveFechaHace){
			if(mensaje.contains(z)){
				return 2;
			}
		}
		for(String a: palabrasClaveDiasPasaron){
			if(mensaje.contains(a)){
				return 3;
			}
		}
		for(String b: palabrasClaveDiasFaltan){
			if(mensaje.contains(b)){
				return 4;
			}
		}
		return -1;
	}
	
	private String hola(){
		return "�Hola, @" + TestAsistente.USUARIO + "!";
	}
	
	private String fechaACadena(Calendar f1){
		Date d1=f1.getTime();
		SimpleDateFormat formato = 
			    new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
			return formato.format(d1);
	}
	
	private Calendar cadenaAFecha(String f1) throws ParseException{
		//recibe todo el mensaje
		if(!f1.contains("(\\d{4})")){
			return cadenaAFechaSinA�o(f1);
		} else{
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
	
	private Calendar cadenaAFechaSinA�o(String f1) throws ParseException{
		//recibe todo el mensaje
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
	private String fechaDentro(Calendar f1){
		
		return "@" +TestAsistente.USUARIO + " ser� el "+fechaACadena(f1);
	}
	
	private String fechaHace(Calendar f1){
		
		return "@" +TestAsistente.USUARIO + " fue el "+fechaACadena(f1);
	}
	
	private String diasPasaron(long dias,Calendar f1,Calendar f2){
		return "@" +TestAsistente.USUARIO + " entre el "+fechaACadena(f2)+" y el "+fechaACadena(f1)+" pasaron "+(dias-1)+" d�as";
	}

	private String diasFaltan(long dias,Calendar f1,Calendar f2){
		return "@" +TestAsistente.USUARIO +" faltan "+dias+" d�as";
	}
	
	/*private String enviarMensaje(String mensaje){
		System.out.println(mensaje);
		return mensaje;
	}*/
}
