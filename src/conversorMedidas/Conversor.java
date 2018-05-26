package conversorMedidas;

import java.util.HashMap;

public abstract class Conversor {
	
	
	protected HashMap<String, Double> units;	
	
	public final String Convert(String medidaAConvertir, String valor, String medidaInicial) {		
		if(units.containsKey(medidaAConvertir) && units.containsKey(medidaInicial)){
			double conversion = Double.parseDouble(valor) * units.get(medidaInicial) / units.get(medidaAConvertir);
			return " son" + conversion + medidaAConvertir;	
		}
		else
			return " las unidades especificadas no se pueden transformar";				
	}
	/*
	 * public class Converter
{
    public static void main (String[] args)
    {
        Converter converter = new Converter();

        Set<String> units = converter.availableUnits();

        double value = 5.4;

        for (String unitFrom : units)
        {
            System.out.println(value + " " + unitFrom + " are:");

            for (String unitTo : units)
            {
                System.out.println("\t" + converter.convert(unitFrom, unitTo, value) + " " + unitTo);
            }

            System.out.println();
        }
    }    
	 * */
	
}
