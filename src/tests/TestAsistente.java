package tests;
import org.junit.*;

import asistente.Asistente;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class TestAsistente{
	
	
	public final static String USUARIO = "delucas";
	public final static Calendar FECHA_MUNDIAL = Calendar.getInstance();

	Asistente shannon;
	
	@Before
	public void setup(){
		shannon = new Asistente("shannon");
		FECHA_MUNDIAL.set(Calendar.YEAR,2018);
		FECHA_MUNDIAL.set(Calendar.MONTH,5);
		FECHA_MUNDIAL.set(Calendar.DAY_OF_MONTH,14);
	}
	
	@Test
	public void noTeEntiendo() throws ParseException, IOException{
		String mensaje = "chacha @shannon";		
		Assert.assertEquals("Disculpa... no entiendo el pedido, @pepe ¿podrías repetirlo?", shannon.escuchar(mensaje));
	}
	
	@Test
	public void saludo() throws ParseException, IOException{
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("¡Hola, @delucas!", shannon.escuchar(mensaje));
	}
	

	@Test
	public void hora() throws ParseException, IOException {
		String[] mensajes = {
				"¿qué hora es, @shannon?",
				"@shannon, la hora por favor",
				"me decís la hora @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas son las 01:14 AM",
					shannon.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void fecha() throws ParseException, IOException {
		String[] mensajes = {
				"¿qué día es, @shannon?",
				"@shannon, la fecha por favor",
				"me decís la fecha @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es 2 de junio de 2018",
					shannon.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void diaDeLaSemana() throws ParseException, IOException {
		String[] mensajes = {
				"¿qué día de la semana es hoy, @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es sábado",
					shannon.escuchar(mensaje)
			);
		}
}
}
