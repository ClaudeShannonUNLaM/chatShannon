package tests;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF5Test {
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void TriviasFacts() throws ParseException {
		Assert.assertEquals(
				"Cleopatra vivió más cerca de la invención del iPhone que de la construcción de la Gran Pirámide.",
				jenkins.escuchar("@jenkins quiero datos de trivia")
			);
		
		Assert.assertEquals(
				"Cleopatra vivió más cerca de la invención del iPhone que de la construcción de la Gran Pirámide.",
				jenkins.escuchar("@jenkins dame datos importantes")
			);
		
		Assert.assertEquals(
				"Cleopatra vivió más cerca de la invención del iPhone que de la construcción de la Gran Pirámide.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);		
	}
}
