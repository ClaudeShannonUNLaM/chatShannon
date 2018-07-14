package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;
import bot.meme.GeneradorMeme;

public class RF20Tests {
	public final static String USUARIO = "delucas"; 
	Asistente shannon;

	
	@Before
	public void setup() {
		shannon = new Asistente("shannon");
	
	}
	
	@Test
	public void wikiBuscadorTest() throws ParseException, IOException{
	
		assertEquals("mina clavero",shannon.escuchar("@shannon, quiero buscar en wikipedia mina clavero"));
	}
	
}
