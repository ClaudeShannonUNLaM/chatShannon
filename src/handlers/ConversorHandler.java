package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conversorMedidas.Conversor;
import conversorMedidas.ConversorCapacidad;
import conversorMedidas.ConversorLongitud;
import conversorMedidas.ConversorMasa;
import conversorMedidas.ConversorTiempo;

public class ConversorHandler extends AsistantSentenceHandler {

	private final Pattern patron = Pattern.compile("(?:cuántos|cuántas|cuantas|cuantos) (gramos|kilos|onzas|hectogramos|libras|dracmas|kilómetros|kilómetros|metros|centimetros|pulgadas|pies|yarda|horas|minutos|segundos|decalitros|litros|centilitros|pulgadas cúbicas|pulgadas cubicas|pies cúbicos|pies cubicos|yardas cúbicas|yardas cubicas|) (?:son|hay en) ([0-9]+) (gramos|kilos|onzas|hectogramos|libras|dracmas|kilómetros|kilómetros|metros|centimetros|pulgadas|pies|yarda|horas|minutos|segundos|decalitros|litros|centilitros|pulgadas cúbicas|pulgadas cubicas|pies cúbicos|pies cubicos|yardas cúbicas|yardas cubicas| gramo|kilo|onza|hectogramo|libra|dracma|kilómetro|kilómetro|metro|centimetro|pulgada|pie|yard|hora|minuto|segundo|decalitro|litro|centilitro|pulgada cúbica|pulgada cubica|pie cúbico|pie cubico|yarda cúbica|yarda cubica)");
	
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
