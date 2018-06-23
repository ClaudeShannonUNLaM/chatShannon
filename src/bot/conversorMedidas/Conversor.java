package bot.conversorMedidas;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

public abstract class Conversor {	
	
	protected HashMap<String, Double> units;	
	
	public final String Convert(String medidaAConvertir, String valor, String medidaInicial) {		
		
		if(units.containsKey(medidaAConvertir) && units.containsKey(medidaInicial)){
			double conversion = Double.parseDouble(valor) * units.get(medidaInicial) / units.get(medidaAConvertir);
			
			DecimalFormat df;
			if(conversion -  Math.floor(conversion) == 0)
				df = new DecimalFormat("#");
			else			
				df = new DecimalFormat("#.##"); //Se redondea a dos decimales
			
			df.setRoundingMode(RoundingMode.DOWN);
			String s = df.format(conversion);
			
			return " " + valor + " "+ medidaInicial + " equivalen a " + s +" " + medidaAConvertir;
		}
		else
			return " las unidades especificadas no se pueden transformar";				
	}	
	
}
