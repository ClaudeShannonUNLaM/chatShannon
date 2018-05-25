package conversorMedidas;

public interface Conversor {
	
	String Convert(String medidaAConvertir ,String valor,String medidaInicial);
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

    private HashMap<String, Double> _units;

    public Converter()
    {
        _units = new HashMap<String, Double>();

        _units.put("Millimetres", 1.0);
        _units.put("Metres", 1000.0);
        _units.put("Inches", 25.4);
        _units.put("Feet", 304.8);
        _units.put("Yards", 914.4);
    }

    public double convert(String from, String to, double value)
    {
        return value * _units.get(from) / _units.get(to);
    }

    private Set<String> availableUnits()
    {
        return _units.keySet();
    }
}
	 * 
	 * */
	
}
