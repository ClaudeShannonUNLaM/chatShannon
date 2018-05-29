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
		
		Assert.assertEquals("¡Hola, @pepe!", shannon.escuchar(mensaje));
	}
	@Test
	public void diaDentroDe() throws ParseException, IOException {
		Assert.assertEquals(
				"@pepe será el sábado 5 de mayo de 2018",
				shannon.escuchar("@shannon qué día será dentro de 2 días?")
			);
		Assert.assertEquals(
				"@pepe será el martes 3 de julio de 2018",
				shannon.escuchar("@shannon qué día será dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe será el domingo 3 de mayo de 2020",
				shannon.escuchar("@shannon qué día será dentro de 2 años?")
			);
			
	}
	
	@Test
	public void diaHace() throws ParseException, IOException {
		
	Assert.assertEquals(
				"@pepe fue el miércoles 2 de mayo de 2018",
				shannon.escuchar("@shannon qué día fue ayer?")
			);
		
		Assert.assertEquals(
				"@pepe fue el lunes 30 de abril de 2018",
				shannon.escuchar("@shannon qué día fue hace 3 días?")
			);
		
		Assert.assertEquals(
				"@pepe fue el sábado 3 de marzo de 2018",
				shannon.escuchar("@shannon qué día fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe fue el martes 3 de mayo de 2016",
				shannon.escuchar("@shannon qué día fue hace 2 años?")
			);			
	}
	@Test
	public void tiempoDesde() throws ParseException, IOException {
		Assert.assertEquals(
				"@pepe entre el 4 de mayo de 2017 y el 4 de mayo de 2018 pasaron 365 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 4 de mayo de 2017?")
			);
		Assert.assertEquals(
				"@pepe entre el 4 de abril de 2018 y el 4 de mayo de 2018 pasaron 30 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 4 de abril?")
			);		 
	}
	
	@Test
	public void tiempoHasta() throws ParseException, IOException {
		Assert.assertEquals(
				"@pepe faltan 1 días",
				shannon.escuchar("@shannon cuántos días faltan para el 5 de mayo?")
			);
		Assert.assertEquals(
				"@pepe faltan 361 días",
				shannon.escuchar("@shannon cuántos días faltan para el 30 de abril de 2019?")
			);
		Assert.assertEquals(
				"@pepe faltan 42 días",
				shannon.escuchar("@shannon cuántos días faltan para el mundial?")
			);
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
					"@pepe son las 01:14 AM",
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
					"@pepe hoy es 2 de mayo de 2018",
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
					"@pepe hoy es miércoles",
					shannon.escuchar(mensaje)
			);
		}
}
}
