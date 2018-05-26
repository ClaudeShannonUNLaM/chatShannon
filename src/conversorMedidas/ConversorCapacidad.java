package conversorMedidas;

import java.util.HashMap;

public class ConversorCapacidad extends Conversor {

	public ConversorCapacidad() {
		units = new HashMap<String, Double>();
		units.put("Millimetres", 1.0);
        units.put("Metres", 1000.0);
        units.put("Inches", 25.4);
        units.put("Feet", 304.8);
        units.put("Yards", 914.4);
	}	

	/*Decalitro = 10 litros
	 * litro 
	 * Centilitro = litro/1000
	 * 
	 * 
	 * 1 pulgada c�bica (in� o cu in) = 16,387064 cm� (ml)
			1 pie c�bico (ft� o cu ft) = 1728 in� = 28,316846592 dm� (l)
			1 yarda c�bica (yd� o cu yd) = 27 ft� = 764,554857984 dm� (l)
	 * 
	 * */
}
