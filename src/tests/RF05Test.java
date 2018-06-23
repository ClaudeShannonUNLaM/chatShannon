package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

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
				"Cleopatra vivió más cerca de la invención del iPhone que de la construcción de la Gran Pirámide.",
				jenkins.escuchar("@jenkins quiero datos de trivia")
			);
		
		Assert.assertEquals(
				"Rusia tiene una superficie más grande que Plutón.",
				jenkins.escuchar("@jenkins dame datos importantes")
			);
		
		Assert.assertEquals(
				"Arabia Saudí importa camellos de Australia.",
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
				"Las zanahorias eran originalmente de color púrpura.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"El corazón de una ballena azul es tan grande que un ser humano podría nadar a través de las arterias.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Una maquina expendedora puede matarte más fácilmente que un tiburón.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"La Universidad de Oxford es más antigua que el imperio azteca.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Francia seguía ejecutando personas con una guillotina cuando la primera película de Star Wars se estrenó.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Los armadillos casi siempre dan a luz a cuatrillizos idénticos.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"Eduard Punset es en realidad más viejo que el pan de molde.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		Assert.assertEquals(
				"El unicornio es el animal nacional de Escocia.",
				jenkins.escuchar("@jenkins quiero saber algo interesante")
			);	
		
	}
}
