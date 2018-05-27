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
				"Cleopatra vivi� m�s cerca de la invenci�n del iPhone que de la construcci�n de la Gran Pir�mide.",
				jenkins.escuchar("@jenkins quiero datos de trivia")
			);
		
		Assert.assertEquals(
				"Cleopatra vivi� m�s cerca de la invenci�n del iPhone que de la construcci�n de la Gran Pir�mide.",
				jenkins.escuchar("@jenkins dame datos importantes")
			);
		
		Assert.assertEquals(
				"Cleopatra vivi� m�s cerca de la invenci�n del iPhone que de la construcci�n de la Gran Pir�mide.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);		
	}
}
