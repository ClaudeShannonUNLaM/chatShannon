package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

public class RF02Tests {

	public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void agradecimiento() throws ParseException,IOException{
		String[] mensajes = {
				"Muchas gracias, @jenkins!",
				"@jenkins gracias",
				"gracias @jenkins"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"No es nada, @delucas",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
}