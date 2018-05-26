package conversorMedidas;

import java.util.HashMap;

public class ConversorMasa extends Conversor {
	
	public ConversorMasa() {
		units = new HashMap<String, Double>();
		units.put("gramo",1.0);
        units.put("kilo", 1000.0);
        units.put("hectogramo", 100.0);        
        units.put("onza", 28.3495);        
        units.put("libra", 453.592);
        units.put("dracma", 1.772);
        
        units.put("gramos",1.0);
        units.put("kilos", 1000.0);
        units.put("hectogramos", 100.0);        
        units.put("onzas", 28.3495);        
        units.put("libras", 453.592);
        units.put("dracmas", 1.772);
	}	
}
