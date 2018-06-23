package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChuckNorrisHandler extends AsistantSentenceHandler {

	private String[] facts = {"Chuck Norris arrojó una granada y mató a 50 personas, luego explotó.",
			"Chuck Norris contó hasta el infinito. Dos veces.",
			"Chuck Norris puede matar dos piedras con un pájaro.",
			"Chuck Norris puede recoger naranjas de un manzano y hacer la mejor limonada que haya probado en su vida.",
			"Chuck Norris puede escuchar lenguaje de señas.",
			"Las jirafas fueron creadas cuando Chuck Norris golpeó un caballo.",
			"Una vez, una cobra mordió la pierna de Chuck Norris. Después de cinco días de dolor insoportable, la cobra murió.",			
			"Cuando Chuck Norris estaba en la escuela secundaria, su profesor de inglés le asignó un ensayo: '¿Qué es el coraje?' Recibió un A + por entregar una página en blanco con solo su nombre en la parte superior.",
			"No existe una teoría de la evolución, solo una lista de las criaturas que Chuck Norris permite vivir.",
			"Chuck Norris alimentó a su madre a través del cordón umbilical mientras lo llevaba.",
			"Cuando Chuck Norris se fue a la universidad, miró a su padre. Y dijo: 'Ahora eres el hombre de la casa'.",
			"Chuck Norris puede pararse en el fondo de un pozo sin fondo.",
			"Chuck Norris nunca tendrá un ataque al corazón ... incluso un corazón no es tan tonto como para atacar a Chuck Norris.",
			"Chuck Norris puede matar a tus amigos imaginarios.",
			"Chuck Norris golpeó al sol en un concurso de miradas.",
			"Chuck Norris es la única persona que puede golpear un cíclope entre los ojos.",
			"Se considera un gran logro bajar por las cataratas del Niágara en un barril de madera. Chuck Norris puede subir a las Cataratas del Niágara en una caja de cartón.",
			"Chuck Norris puede construir un muñeco de nieve con lluvia.",
			"Chuck Norris puede estrangularte con un teléfono inalámbrico.",
			"En una ocasión, Chuck Norris fue acusado de tres intentos de asesinato en el condado de Boulder, pero el juez rápidamente retiró los cargos porque Chuck Norris no 'intentó' el asesinato.",
			"Los principales desinfectantes para manos afirman que pueden matar el 99.9 por ciento de los gérmenes. Chuck Norris puede matar al 100 por ciento de lo que sea que él quiera.",
			"El calendario de Chuck Norris va directamente del 31 de marzo al 2 de abril. Nadie engaña a Chuck Norris.",
			"Chuck Norris puede frotar dos pedazos de fuego y hacer madera.",
			"Cuando comienza un apocalipsis zombie, Chuck Norris no intenta sobrevivir. Los zombies lo hacen.",
			"Chuck puede prender fuego a las hormigas con una lupa. Por la noche.",
			"Chuck Norris una vez fue a Marte. Es por eso que no hay signos de vida.",
			"Chuck Norris hace llorar a las cebollas.",
			"Chuck Norris conoce el secreto de Victoria.",
			"La razón por la que el Santo Grial nunca se ha recuperado es porque nadie es lo suficientemente valiente como para pedirle a Chuck Norris que renuncie a su taza de café favorita.",
			"Cuando Bruce Banner se enoja, se convierte en Hulk. Cuando Hulk se enoja se convierte en Chuck Norris. Cuando Chuck Norris se enoja, corre.",
			"Chuck Norris es la razón por la que Waldo se está escondiendo.",
			"El tipo de sangre de Chuck Norris es AK-47."};	
	
	private File archivo;
	
	public ChuckNorrisHandler(){		
		archivo = new File("ChuckNorrisHandler.txt");
		patron = Pattern.compile(".*(?:dame|quiero).*(?:chuck norris|Chuck Norris)");
	}
	
	@Override
	public String giveAnswer(String mensaje, String nombreUsuario){
		Matcher matcher = patron.matcher(mensaje);		
	    if (matcher.matches()) {
	    	Scanner s = null;
			try {
				String respuesta = "";
				s = new Scanner(archivo);
				int NorrisIndex = s.nextInt();
				s.close();
				
				if(NorrisIndex == facts.length){
					NorrisIndex = 1;					
					respuesta = facts[0];
				}
				else{
					NorrisIndex++;
					respuesta = facts[NorrisIndex - 1];
				}			
							
				PrintWriter pw = new PrintWriter(archivo);
				pw.print(NorrisIndex);
				pw.close();
				return respuesta;
								
			} catch (FileNotFoundException e) {				
				return "Hubo un error abriendo el archivo";
			}	        		    	
	    } else 
	    	return this.nextHandler.giveAnswer(mensaje, nombreUsuario);			
	}		
}
