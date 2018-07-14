package bot.datosFinancieros;

public class ConsultaCotizacion {
	
	private String respuesta;
	private int indiceMenor;
	private int indiceMayor;
	private String apiWeb;
	
	public ConsultaCotizacion(String resp,int indexMenor,int indexMayor,String api) {
		respuesta= new String (resp);
		indiceMenor=indexMenor;
		indiceMayor =indexMayor;
		apiWeb = new String (api);
	}

	public String getRespuesta() {
		return respuesta;
	}

	public int getIndiceMenor() {
		return indiceMenor;
	}

	public int getIndiceMayor() {
		return indiceMayor;
	}

	public String getApiWeb() {
		return apiWeb;
	}	
}
