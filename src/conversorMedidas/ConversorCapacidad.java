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
	 * 1 pulgada cúbica (in³ o cu in) = 16,387064 cm³ (ml)
			1 pie cúbico (ft³ o cu ft) = 1728 in³ = 28,316846592 dm³ (l)
			1 yarda cúbica (yd³ o cu yd) = 27 ft³ = 764,554857984 dm³ (l)
	 * 
	 * */
}
