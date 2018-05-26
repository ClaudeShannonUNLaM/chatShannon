package conversorMedidas;

import java.util.HashMap;

public class ConversorLongitud extends Conversor {	
	
	public ConversorLongitud() {
		units = new HashMap<String, Double>();
		units.put("centimetro", 1.0);
        units.put("metro", 100.0);
        units.put("kilometro", 1000000.0);
        units.put("kilómetro", 1000000.0);
        units.put("pulgadas", 2.54);
        units.put("pie", 30.48);
        units.put("yarda", 91.44);
        units.put("centimetros", 1.0);
        units.put("metros", 100.0);
        units.put("kilometros", 1000000.0);
        units.put("kilómetros", 1000000.0);
        units.put("pulgadass", 2.54);
        units.put("pies", 30.48);
        units.put("yardas", 91.44);
	}
	
	/*
	 * case "kilómetros":
			case "kilometros":
			case "metros":
			case "centimetros":
			
			case "pulgadas":
			case "pies":
			case "yardas":
	 * */

	/*
	 * kilómetro (km): 103 metros = 1 000 metros
	 * metro = 100
	 * centrimetro
	 * 
	 * 
	 * 1 pulgada (in) = 2,54 cm
	   1 pie (ft) = 12 in = 30,48 cm
	   1 yarda (yd) = 3 ft = 91,44 cm
	 * 
	 * */
	
}
