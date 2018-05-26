package conversorMedidas;

import java.util.HashMap;

public class ConversorTiempo extends Conversor {	

	public ConversorTiempo() {
		units = new HashMap<String, Double>();
		units.put("segundo",1.0);
		units.put("minuto", 60.0);
        units.put("hora", 3600.0);
        
        
        units.put("segundos",1.0);
		units.put("minutos", 60.0);
        units.put("horas", 3600.0);
	}	
}
