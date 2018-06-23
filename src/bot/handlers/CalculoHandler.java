package bot.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

	

public class CalculoHandler extends AsistantSentenceHandler{
	public CalculoHandler () {
			patron = Pattern.compile("(?:(cuánto es el|cuánto es))");
	}
		
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
	    if(matcher.find()) {
			String intro;
			if(mensaje.contains("cuánto es el"))
				
				intro = "@"+ nombreUsuario + " cuánto es el";
			else
				intro = "@" + nombreUsuario + " cuánto es";
			Integer result = resolverCalculo(mensaje.substring(intro.length()));
			mensaje = "@" + nombreUsuario + " " + result.toString();
			
			return mensaje;	
		}
		else
			return this.nextHandler.giveAnswer(mensaje, nombreUsuario);				
	}
	
	public int resolverCalculo (String calculo) {
		int posOper, a, b;
		int posIni;
		
		calculo = calculo.replace(" ","");

		while ((posIni=calculo.indexOf('(')) != -1){
			while(calculo.substring(posIni+1).indexOf('(') < calculo.substring(posIni+1).indexOf(')') && calculo.substring(posIni+1).indexOf('(') != -1)
				posIni= calculo.substring(posIni+1).indexOf('(') + posIni + 1;
			calculo = calculo.replace(calculo.substring(posIni, calculo.substring(posIni).indexOf(')')+1+posIni), String.valueOf(resolverCalculo(calculo.substring(posIni+1, calculo.substring(posIni).indexOf(')')+posIni))));
		}
			
		if((posOper=calculo.indexOf('+')) != -1) {
			a= resolverCalculo(calculo.substring(0, posOper).trim());
			b= resolverCalculo(calculo.substring(posOper+1).trim());
			return a+b;
		}
		if((posOper=calculo.indexOf('-')) != -1 && posOper > 0) {
			a= resolverCalculo(calculo.substring(0, posOper).trim());
			b= resolverCalculo(calculo.substring(posOper+1).trim());
			return a-b;
		}
		if((posOper=calculo.indexOf('*')) != -1) {
			a= resolverCalculo(calculo.substring(0, posOper).trim());
			b= resolverCalculo(calculo.substring(posOper+1).trim());
			return a*b;
		}
		if((posOper=calculo.indexOf('/')) != -1) {
			a= resolverCalculo(calculo.substring(0, posOper).trim());
			b= resolverCalculo(calculo.substring(posOper+1).trim());
			return (a/b);
		}
		if((posOper=calculo.indexOf("%de")) != -1) {
			return Integer.parseInt(calculo.substring(0, posOper).trim())*100/Integer.parseInt(calculo.substring(posOper+3).trim());
		}
		if((posOper=calculo.indexOf('^')) != -1) {
			a= resolverCalculo(calculo.substring(0, posOper).trim());
			b= resolverCalculo(calculo.substring(posOper+1).trim());
			return (int) Math.pow(a, b);
		}
		
		return Integer.parseInt(calculo);			
	}
	

}
