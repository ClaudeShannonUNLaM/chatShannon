package tests;

import java.text.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF13Tests {	
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void ChuckNorrisFacts() throws ParseException {
		Assert.assertEquals(
				"Chuck Norris arrojó una granada y mató a 50 personas, luego explotó.",
				jenkins.escuchar("@jenkins quiero datos de chuck norris")
			);
		
		Assert.assertEquals(
				"Chuck Norris contó hasta el infinito. Dos veces.",
				jenkins.escuchar("@jenkins dame datos de chuck norris")
			);
		
		Assert.assertEquals(
				"Chuck Norris puede matar dos piedras con un pájaro.",
				jenkins.escuchar("@jenkins quiero saber algo de chuck norris")
			);		
	}
	
}
