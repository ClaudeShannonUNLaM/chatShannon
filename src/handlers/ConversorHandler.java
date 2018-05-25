package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conversorMedidas.Conversor;
import conversorMedidas.ConversorCapacidad;
import conversorMedidas.ConversorLongitud;
import conversorMedidas.ConversorMasa;
import conversorMedidas.ConversorTiempo;

public class ConversorHandler extends AsistantSentenceHandler {

	private final Pattern patron = Pattern.compile("(?:cu�ntos|cu�ntas|cuantas|cuantos) (gramos|kilos|onzas|hectogramos|libras|dracmas|kil�metros|kil�metros|metros|centimetros|pulgadas|pies|yarda|horas|minutos|segundos|decalitros|litros|centilitros|pulgadas c�bicas|pulgadas cubicas|pies c�bicos|pies cubicos|yardas c�bicas|yardas cubicas|) (?:son|hay en) ([0-9]+) (gramos|kilos|onzas|hectogramos|libras|dracmas|kil�metros|kil�metros|metros|centimetros|pulgadas|pies|yarda|horas|minutos|segundos|decalitros|litros|centilitros|pulgadas c�bicas|pulgadas cubicas|pies c�bicos|pies cubicos|yardas c�bicas|yardas cubicas| gramo|kilo|onza|hectogramo|libra|dracma|kil�metro|kil�metro|metro|centimetro|pulgada|pie|yard|hora|minuto|segundo|decalitro|litro|centilitro|pulgada c�bica|pulgada cubica|pie c�bico|pie cubico|yarda c�bica|yarda cubica)");
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
	    Matcher matcher = patron.matcher(mensaje);

	    if (matcher.matches()) {
	    	Conversor conversor;	    	
	    	
	        return conversor.Convert(matcher.group(1),matcher.group(2),matcher.group(3));
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	public Conversor definirConversor(String unidad){
		
		switch(unidad){
			case "hola":
				return new ConversorCapacidad();
			break;
			case "d":
				return new ConversorLongitud();
			break;
			case "daw":
				return new ConversorMasa();
			break;
			case "la":
				return new ConversorTiempo();
			break;
		}
		return null;
		
	}

}
