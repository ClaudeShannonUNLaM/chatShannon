package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class RF14Tests {
	public final static String USUARIO = "delucas";


	Asistente shannon;
	
	@Before
	public void setup(){
		shannon = new Asistente("shannon");
	}
	
	@Test
	public void testBasico() throws ParseException, IOException{
		String mensaje = "@shannon con @roberto y @mica gastamos $50 y pagó @roberto";
		
		Assert.assertEquals("¡Hola, @"+USUARIO+"!", shannon.escuchar(mensaje));
	}
	
}
