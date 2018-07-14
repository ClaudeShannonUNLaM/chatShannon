package tests;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bot.asistente.Asistente;

public class RF10Tests {

	public final static String USUARIO = "delucas";
	public static int ELEGIDO = 12;

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void adivinando() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas ¡sale y vale! Pensá un numero del 1 al 100",
				jenkins.escuchar("@jenkins jugamos?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¿es el 50?",
				jenkins.escuchar("@jenkins listo",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¿es el 75?",
				jenkins.escuchar("@jenkins más grande",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¿es el 62?",
				jenkins.escuchar("@jenkins más chico",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¿es el 68?",
				jenkins.escuchar("@jenkins más grande",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas fue divertido :)",
				jenkins.escuchar("@jenkins si!",USUARIO).getMensaje()
			);
	}
//	@Test
//	public void adivinando3() throws ParseException, IOException {
//		Assert.assertEquals(
//				"@delucas ¡sale y vale! Pensa un numero del 1 al 100",
//				jenkins.escuchar("@jenkins jugamos?",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 50?",
//				jenkins.escuchar("@jenkins listo",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 75?",
//				jenkins.escuchar("@jenkins mas grande",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 87?",
//				jenkins.escuchar("@jenkins mas grande",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 81?",
//				jenkins.escuchar("@jenkins mas chico",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas fue divertido :)",
//				jenkins.escuchar("@jenkins si!",USUARIO)
//			);
//	}
//	@Test
//	public void adivinando2() throws ParseException, IOException {
//		Assert.assertEquals(
//				"@delucas ¡sale y vale! Pensa un numero del 1 al 100",
//				jenkins.escuchar("@jenkins jugamos?",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 50?",
//				jenkins.escuchar("@jenkins listo",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 25?",
//				jenkins.escuchar("@jenkins mas chico",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 12?",
//				jenkins.escuchar("@jenkins mas chico",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas ¿es el 18?",
//				jenkins.escuchar("@jenkins mas grande",USUARIO)
//			);
//		
//		Assert.assertEquals(
//				"@delucas fue divertido :)",
//				jenkins.escuchar("@jenkins si!",USUARIO)
//			);
//	}
	
	@Test
	public void pensandoNumero12() throws ParseException, IOException {
		ELEGIDO=12;
		Assert.assertEquals(
				"@delucas ¡listo!",
				jenkins.escuchar("@jenkins jugamos? Pensá un numero del 1 al 100",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas más chico",
				jenkins.escuchar("@jenkins es el 50?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¡si! Adivinaste!",
				jenkins.escuchar("@jenkins es el 12?",USUARIO).getMensaje()
			);
	}

	@Test
	public void pensandoNumero73() throws ParseException, IOException {
		ELEGIDO = 73;
		Assert.assertEquals(
				"@delucas ¡listo!",
				jenkins.escuchar("@jenkins jugamos? Pensá un numero del 1 al 100",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas más grande",
				jenkins.escuchar("@jenkins es el 50?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas más chico",
				jenkins.escuchar("@jenkins es el 92?",USUARIO).getMensaje()
			);
		
		Assert.assertEquals(
				"@delucas ¡si! Adivinaste!",
				jenkins.escuchar("@jenkins es el 73?",USUARIO).getMensaje()
		);
		
		Assert.assertEquals(
				"@delucas ¡si! Adivinaste!",
				jenkins.escuchar("@jenkins es el 73?",USUARIO).getMensaje()
			);
		
	}

}