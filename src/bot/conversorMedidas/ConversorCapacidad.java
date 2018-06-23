package bot.conversorMedidas;

import java.util.HashMap;

public class ConversorCapacidad extends Conversor {

	public ConversorCapacidad() {
		units = new HashMap<String, Double>();
		
		units.put("mililitro", 1.0);
        units.put("litro", 1000.0);
        units.put("centilitro", 10.0);
        units.put("pulgada cúbica", 16.3871);
        units.put("pulgada cubica", 16.3871);
        units.put("pie cúbico", 28316.8);
        units.put("pie cubico", 28316.8);
        units.put("yarda cúbica", 764555.0);
        units.put("yarda cubica", 764555.0);
        
        units.put("mililitros", 1.0);
        units.put("litros", 1000.0);
        units.put("centilitros", 10.0);
        units.put("pulgadas cúbicas", 16.3871);
        units.put("pulgadas cubicas", 16.3871);
        units.put("pies cúbicos", 28316.8);
        units.put("pies cubicos", 28316.8);
        units.put("yardas cúbicas", 764555.0);
        units.put("yardas cubicas", 764555.0);        
	}	
}
