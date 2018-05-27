package handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChuckNorrisHandler extends AsistantSentenceHandler {

	private String[] facts = {"Chuck Norris arroj� una granada y mat� a 50 personas, luego explot�.",
			"Chuck Norris cont� hasta el infinito. Dos veces.",
			"Chuck Norris puede matar dos piedras con un p�jaro.",
			"Chuck Norris puede recoger naranjas de un manzano y hacer la mejor limonada que haya probado en su vida.",
			"Chuck Norris puede escuchar lenguaje de se�as.",
			"Las jirafas fueron creadas cuando Chuck Norris golpe� un caballo.",
			"Una vez, una cobra mordi� la pierna de Chuck Norris. Despu�s de cinco d�as de dolor insoportable, la cobra muri�.",
			"Chuck Norris no enga�a a la muerte. �l gana justo y cuadrado.",
			"Cuando Chuck Norris estaba en la escuela secundaria, su profesor de ingl�s le asign� un ensayo: '�Qu� es el coraje?' Recibi� un A + por entregar una p�gina en blanco con solo su nombre en la parte superior.",
			"No existe una teor�a de la evoluci�n, solo una lista de las criaturas que Chuck Norris permite vivir.",
			"Chuck Norris aliment� a su madre a trav�s del cord�n umbilical mientras lo llevaba.",
			"Cuando Chuck Norris se fue a la universidad, mir� a su padre. Y dijo: 'Ahora eres el hombre de la casa'.",
			"Chuck Norris puede pararse en el fondo de un pozo sin fondo.",
			"Chuck Norris nunca tendr� un ataque al coraz�n ... incluso un coraz�n no es tan tonto como para atacar a Chuck Norris.",
			"Chuck Norris puede matar a tus amigos imaginarios.",
			"Chuck Norris golpe� al sol en un concurso de miradas.",
			"Chuck Norris es la �nica persona que puede golpear un c�clope entre los ojos.",
			"Se considera un gran logro bajar por las cataratas del Ni�gara en un barril de madera. Chuck Norris puede subir a las Cataratas del Ni�gara en una caja de cart�n.",
			"Chuck Norris puede construir un mu�eco de nieve con lluvia.",
			"Chuck Norris puede estrangularte con un tel�fono inal�mbrico.",
			"En una ocasi�n, Chuck Norris fue acusado de tres intentos de asesinato en el condado de Boulder, pero el juez r�pidamente retir� los cargos porque Chuck Norris no 'intent�' el asesinato.",
			"Los principales desinfectantes para manos afirman que pueden matar el 99.9 por ciento de los g�rmenes. Chuck Norris puede matar al 100 por ciento de lo que sea que �l quiera.",
			"El calendario de Chuck Norris va directamente del 31 de marzo al 2 de abril. Nadie enga�a a Chuck Norris.",
			"Chuck Norris puede frotar dos pedazos de fuego y hacer madera.",
			"Cuando comienza un apocalipsis zombie, Chuck Norris no intenta sobrevivir. Los zombies lo hacen.",
			"Chuck puede prender fuego a las hormigas con una lupa. Por la noche.",
			"Chuck Norris una vez fue a Marte. Es por eso que no hay signos de vida.",
			"Chuck Norris hace llorar a las cebollas.",
			"Chuck Norris conoce el secreto de Victoria.",
			"La raz�n por la que el Santo Grial nunca se ha recuperado es porque nadie es lo suficientemente valiente como para pedirle a Chuck Norris que renuncie a su taza de caf� favorita.",
			"Cuando Bruce Banner se enoja, se convierte en Hulk. Cuando Hulk se enoja se convierte en Chuck Norris. Cuando Chuck Norris se enoja, corre.",
			"Chuck Norris es la raz�n por la que Waldo se est� escondiendo.",
			"El tipo de sangre de Chuck Norris es AK-47."};
	private int NorrisIndex;
	
	public ChuckNorrisHandler(){
		NorrisIndex = 0;
		patron = Pattern.compile(".*(?:dame|quiero).*(?:chuck norris|Chuck Norris)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario) {
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.matches()) {	    	
	    	NorrisIndex ++;
			if(NorrisIndex == facts.length){
				NorrisIndex = 0;
				return facts[facts.length - 1];
			}			
			return facts[NorrisIndex - 1];
	    	
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}		
}
