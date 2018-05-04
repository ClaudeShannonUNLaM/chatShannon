package asistente;
import org.junit.*;
import java.text.ParseException;
import java.util.Calendar;

public class TestAsistente{
	
	
	public final static String USUARIO = "pepe";
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
	public void noTeEntiendo() throws ParseException{
		String mensaje = "chacha @shannon";
		
		Assert.assertEquals("Disculpa... no entiendo el pedido, @pepe ¿podrías repetirlo?", shannon.escuchar(mensaje));
	}
	
	@Test
	public void saludo() throws ParseException{
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("¡Hola, @pepe!", shannon.escuchar(mensaje));
	}
	@Test
	public void diaDentroDe() throws ParseException {
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
	public void diaHace() throws ParseException {
		
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
	public void tiempoDesde() throws ParseException {
		Assert.assertEquals(
				"@pepe entre el sábado 29 de abril de 2017 y el jueves 3 de mayo de 2018 pasaron 369 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 29 de abril de 2017?")
			);
		Assert.assertEquals(
				"@pepe entre el lunes 2 de abril de 2018 y el jueves 3 de mayo de 2018 pasaron 31 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 2 de abril?")
			);		 
	}
	
	@Test
	public void tiempoHasta() throws ParseException {
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
}
