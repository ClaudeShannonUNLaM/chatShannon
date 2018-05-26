package conversorMedidas;

import java.util.HashMap;

public class ConversorLongitud extends Conversor {	
	
	public ConversorLongitud() {
		units = new HashMap<String, Double>();
		units.put("centimetro", 1.0);
        units.put("metro", 100.0);
        units.put("kilometro", 100000.0);
        units.put("kilómetro", 100000.0);
        units.put("pulgadas", 2.54);
        units.put("pie", 30.48);
        units.put("yarda", 91.44);
        
        units.put("centimetros", 1.0);
        units.put("metros", 100.0);
        units.put("kilometros", 100000.0);
        units.put("kilómetros", 100000.0);
        units.put("pulgadass", 2.54);
        units.put("pies", 30.48);
        units.put("yardas", 91.44);
	}	
	
}
