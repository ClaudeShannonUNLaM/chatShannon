package bot.datosFinancieros;

public class ConsultaCotizacion {
	
	public String respuesta;
	public int indiceMenor;
	public int indiceMayor;
	public String apiWeb;
	
	public ConsultaCotizacion(String resp,int indexMenor,int indexMayor,String api) {
		respuesta= new String (resp);
		indiceMenor=indexMenor;
		indiceMayor =indexMayor;
		apiWeb = new String (api);
	}
}
