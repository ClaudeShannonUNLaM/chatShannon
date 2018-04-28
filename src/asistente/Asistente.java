package asistente;

public class Asistente {
	
	private String nombre;
	
	private final String[] palabrasClavesHola = {"Hola", "buen d√≠a", " buenas tardes", "hey"}; 
	
	Asistente(){
		nombre = "Shannon";
	}
	Asistente(String nombre){
		this.nombre = nombre; 
	}
	
	public String escuchar(String mensaje){
		if(!mensaje.contains("@"+nombre))
			return "";
		
		int accion = encontrarPalabraClave(mensaje);
		
		switch (accion) {
		case 0:
			mensaje = hola();
			break;

		default:
			mensaje = "NO TE ENTIENDO GIL";
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
		return -1;
	}
	
	private String hola(){
		return "°Hola, @" + TestAsistente.USUARIO + "!";
	}
	
	/*private String enviarMensaje(String mensaje){
		System.out.println(mensaje);
		return mensaje;
	}*/
}
