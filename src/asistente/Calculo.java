package asistente;

public class Calculo {
	public static int resolverCalculo (String calculo) {
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
