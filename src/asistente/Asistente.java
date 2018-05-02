package asistente;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import asistente.TestAsistente;
import fecha.CalculadorDiferenciaFechas;
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
		
		switch (accion) {
			case 0:
				mensaje = saludar();
				break;
			case 1:
				CalculadorDiferenciaFechas.calcular(mensaje, false);
				break;
				
			case 2:
				CalculadorDiferenciaFechas.calcular(mensaje, true);
				break;
				
			case 3:
				Calendar fechaPreguntada = Fecha.cadenaAFecha(mensaje);			
				
				long diasDiferencia = Fecha.restarFechas(Fecha.getToday(), fechaPreguntada);
				mensaje = CalculadorDiferenciaFechas.diasPasaron(diasDiferencia,Fecha.getToday(),fechaPreguntada);
				break;
				
			case 4:
				Calendar fechaRequerida;
				if(mensaje.contains("mundial")){
					fechaRequerida = TestAsistente.FECHA_MUNDIAL;
				}else
					fechaRequerida = Fecha.cadenaAFecha(mensaje);
				
				long diasRestantes = Fecha.restarFechas(Fecha.getToday(), fechaRequerida);
				mensaje = CalculadorDiferenciaFechas.diasFaltan(diasRestantes ,Fecha.getToday(),fechaRequerida);
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
	
	private String saludar(){
		return "�Hola, @" + TestAsistente.USUARIO + "!";
	}
	
	
	
	/*private String enviarMensaje(String mensaje){
		System.out.println(mensaje);
		return mensaje;
	}*/
}
