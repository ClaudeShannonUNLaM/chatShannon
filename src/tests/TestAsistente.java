package tests;
import org.junit.*;

import bot.asistente.Asistente;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TestAsistente{
	
	
	public final static String USUARIO = "delucas";
	public final static Calendar FECHA_MUNDIAL = Calendar.getInstance();

	Asistente shannon;
	
	Calendar c = Calendar.getInstance();
	SimpleDateFormat formato;
	
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
		
		Assert.assertEquals("Disculpa... no entiendo el pedido, @"+USUARIO+" ¿podrías repetirlo?", shannon.escuchar(mensaje));
	}
	
	@Test
	public void saludo() throws ParseException, IOException{
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("¡Hola, @"+USUARIO+"!", shannon.escuchar(mensaje));
	}
	

	@Test
	public void hora() throws ParseException, IOException {
		formato = new SimpleDateFormat("hh:mm a");
		String[] mensajes = {
				"¿qué hora es, @shannon?",
				"@shannon, la hora por favor",
				"me decís la hora @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@"+USUARIO+" son las "+formato.format(c.getTime()),
					shannon.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void fecha() throws ParseException, IOException {
		formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		String[] mensajes = {
				"¿qué día es, @shannon?",
				"@shannon, la fecha por favor",
				"me decís la fecha @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@"+USUARIO+" hoy es " + formato.format(c.getTime()),
					shannon.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void diaDeLaSemana() throws ParseException, IOException {
		formato = new SimpleDateFormat("EEEE", new Locale("es","ES"));
		String[] mensajes = {
				"¿qué día de la semana es hoy, @shannon?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@"+USUARIO+" hoy es " + formato.format(c.getTime()),
					shannon.escuchar(mensaje)
			);
		}
}
}
