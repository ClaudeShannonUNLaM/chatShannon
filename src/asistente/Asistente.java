package asistente;

import java.text.ParseException;
import java.util.Calendar;

import asistente.TestAsistente;
import fecha.CalculadorDiferenciaFechas;
import fecha.Fecha;

public class Asistente {
	
	private String nombre;
	private String respuesta;
	
	private final String[] palabrasClavesHola = {"Hola", "buen d�a", " buenas tardes", "hey"}; 
	private final String[] palabrasClaveFechaDentro = {"qu� d�a ser� dentro de","qu� d�a ser� en","qu� d�a ser� ma�ana"}; 
	private final String[] palabrasClaveFechaHace ={"qu� d�a fue hace","qu� d�a fue ayer"};
	private final String[] palabrasClaveDiasPasaron ={"cu�ntos d�as pasaron desde el"};
	private final String[] palabrasClaveDiasFaltan ={"cu�ntos d�as faltan para el"};
	private final String[] palabrasClaveCalculo = {"cu�nto es el ", "cu�nto es "};

	
	Asistente(){
		this("Shannon");
	}
	Asistente(String nombre){
		this.nombre = nombre; 
		/*pratronPreguntaSobreHoy[0] = Pattern.compile("^�qu� ?(hora|d�a) es,");
		pratronPreguntaSobreHoy[1] = Pattern.compile("^la ?(hora|fecha) por favor");*/
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
				mensaje = CalculadorDiferenciaFechas.calcular(mensaje, false);
				break;
				
			case 2:
				mensaje = CalculadorDiferenciaFechas.calcular(mensaje, true);
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
				
				long diasRestantes = Fecha.restarFechas(fechaRequerida,Fecha.getToday());
				mensaje = CalculadorDiferenciaFechas.diasFaltan(diasRestantes);
				break;
			case 100:
				mensaje = respuesta;
				break;
				
			case 5:
				String intro;
				if(mensaje.contains("@"+nombre+" cu�nto es el"))
					intro = "@"+nombre+" cu�nto es el";
				else
					intro = "@"+nombre+" cu�nto es";
				Integer result = Calculo.resolverCalculo(mensaje.substring(intro.length()));
				mensaje = "@" + RF06Tests.USUARIO + " " + result.toString();
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
		for(String c: palabrasClaveCalculo) {
			if(mensaje.contains(c)) {
				return 5;
			}
		}
		String rHoy;
		if(!(rHoy = TiempoActual.preguntaPorHoy(mensaje)).isEmpty()){
			respuesta = TiempoActual.respuestaHoy(mensaje, rHoy);
			return 100;
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
