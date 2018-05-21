package handlers;

public class CalculoHandler extends AsistantSentenceHandler{

	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		if(true){ //if(mensaje cumple con expresion regular)
			String intro;
			if(mensaje.contains("cuánto es el"))
				//Importante: No se deberia usar esta concatenacion del intro como se planteo en la primera entrega ya
				//que esta función no deberia conocer el nombre del asistente.

				//Si todavia se quiere resolver asi entonces agregar una propiedad de esta clase del nombre 
				//del bot y setearla en el metodo escuchar del Asistente.				

				//Se deja la variable nombreUsuario aunque esta este incorrecta para que no tire error en esta primera etapa. 
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
