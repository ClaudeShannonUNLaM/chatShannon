package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF05Test {
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test

	public void TriviasFacts() throws ParseException, IOException {
		Assert.assertEquals(
				"Cleopatra vivi� m�s cerca de la invenci�n del iPhone que de la construcci�n de la Gran Pir�mide.",
				jenkins.escuchar("@jenkins quiero datos de trivia")
			);
		
		Assert.assertEquals(
				"Rusia tiene una superficie m�s grande que Plut�n.",
				jenkins.escuchar("@jenkins dame datos importantes")
			);
		
		Assert.assertEquals(
				"Arabia Saud� importa camellos de Australia.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);
		Assert.assertEquals(
				"El nombre completo del juguete de Barbie es Barbara Millicent Roberts.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Woody, de Toy Story, tiene un nombre completo y es Woody Pride.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Las zanahorias eran originalmente de color p�rpura.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"El coraz�n de una ballena azul es tan grande que un ser humano podr�a nadar a trav�s de las arterias.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Una maquina expendedora puede matarte m�s f�cilmente que un tibur�n.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"La Universidad de Oxford es m�s antigua que el imperio azteca.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Francia segu�a ejecutando personas con una guillotina cuando la primera pel�cula de Star Wars se estren�.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Los armadillos casi siempre dan a luz a cuatrillizos id�nticos.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Eduard Punset es en realidad m�s viejo que el pan de molde.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"El unicornio es el animal nacional de Escocia.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		
	}
}
