package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conversorMedidas.Conversor;
import conversorMedidas.ConversorCapacidad;
import conversorMedidas.ConversorLongitud;
import conversorMedidas.ConversorMasa;
import conversorMedidas.ConversorTiempo;

public class ConversorHandler extends AsistantSentenceHandler {	
	
	public ConversorHandler() {
		patron = Pattern.compile(".*(?:cu�ntos|cu�ntas|cuantas|cuantos) (gramos|kilos|onzas|hectogramos|libras|dracmas|kil�metros|kilometros|metros|centimetros|cent�metros|pulgadas|pies|yardas|horas|minutos|segundos|mililitros|litros|centilitros|pulgadas c�bicas|pulgadas cubicas|pies c�bicos|pies cubicos|yardas c�bicas|yardas cubicas) (?:son|hay en) ([0-9]+) (gramos|kilos|onzas|hectogramos|libras|dracmas|kil�metros|kil�metros|metros|centimetros|cent�metros|pulgadas|pies|yarda|horas|minutos|segundos|mililitros|litros|centilitros|pulgadas c�bicas|pulgadas cubicas|pies c�bicos|pies cubicos|yardas c�bicas|yardas cubicas| gramo|kilo|onza|hectogramo|libra|dracma|kil�metro|kilometro|metro|centimetro|cent�metro|pulgada|pie|yard|hora|minuto|segundo|mililitro|litro|centilitro|pulgada c�bica|pulgada cubica|pie c�bico|pie cubico|yarda c�bica|yarda cubica)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {	    
		Matcher matcher = patron.matcher(mensaje);
		
	    if (matcher.matches()) {	    	
	    	Conversor conversor;	    	
	    	conversor = definirConversor(matcher.group(1));
	    	
	    	if(conversor != null)
	    		return "@" + nombreUsuario + conversor.Convert(matcher.group(1),matcher.group(2),matcher.group(3));
	    	
	    	return "@" + nombreUsuario + " la medida pedida no fue reconocida, lo siento";
	    	
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);
	}
	
	public Conversor definirConversor(String unidad){
		switch(unidad){
			case "gramos":
			case "kilos":
			case "onzas":
			case "hectogramos":
			case "libras":
			case "dracmas":			
				return new ConversorMasa();			
			case "kil�metros":
			case "kilometros":
			case "metros":
			case "centimetros":
			case "cent�metros":
			case "pulgadas":
			case "pies":
			case "yardas":
				return new ConversorLongitud();			
			case "mililitros":
			case "litros":
			case "centilitros":
			case "pulgadas c�bicas":
			case "pulgadas cubicas":
			case "pies c�bicos":
			case "pies cubicos":
			case "yardas c�bicas":
			case "yardas cubicas":
				return new ConversorCapacidad();			
			case "horas":
			case "minutos":
			case "segundos":
				return new ConversorTiempo();			
		}
		
		return null;
	}

}
