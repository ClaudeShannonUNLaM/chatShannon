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
		/*Assert.assertEquals(
				"@pepe será el martes 1 de mayo de 2018",
				shannon.escuchar("@shannon qué día será dentro de 1 días?")
			);
		Assert.assertEquals(
				"@pepe será el sábado 30 de junio de 2018",
				shannon.escuchar("@shannon qué día será dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe será el jueves 30 de abril de 2020",
				shannon.escuchar("@shannon qué día será dentro de 2 años?")
			);
			*/
	}
	
	@Test
	public void diaHace() throws ParseException {
		
	/*	Assert.assertEquals(
				"@pepe fue el domingo 29 de abril de 2018",
				shannon.escuchar("@shannon qué día fue ayer?")
			);
		
		Assert.assertEquals(
				"@pepe fue el viernes 27 de abril de 2018",
				shannon.escuchar("@shannon qué día fue hace 3 días?")
			);
		
		Assert.assertEquals(
				"@pepe fue el miércoles 28 de febrero de 2018",
				shannon.escuchar("@shannon qué día fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe fue el sábado 30 de abril de 2016",
				shannon.escuchar("@shannon qué día fue hace 2 años?")
			);
			*/
	}
	@Test
	public void tiempoDesde() throws ParseException {
		/*Assert.assertEquals(
				"@pepe entre el sábado 29 de abril de 2017 y el lunes 30 de abril de 2018 pasaron 366 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 29 de abril de 2017?")
			);
		Assert.assertEquals(
				"@pepe entre el lunes 2 de abril de 2018 y el lunes 30 de abril de 2018 pasaron 28 días",
				shannon.escuchar("@shannon cuántos días pasaron desde el 2 de abril?")
			);
		*/
		// agregar casos de prueba
	}
	
	@Test
	public void tiempoHasta() throws ParseException {
		/*Assert.assertEquals(
				"@pepe faltan 2 días",
				shannon.escuchar("@shannon cuántos días faltan para el 2 de mayo?")
			);
		Assert.assertEquals(
				"@pepe faltan 365 días",
				shannon.escuchar("@shannon cuántos días faltan para el 30 de abril de 2019?")
			);
		Assert.assertEquals(
				"@pepe faltan 45 días",
				shannon.escuchar("@shannon cuántos días faltan para el mundial?")
			);	
			*/	
		// agregar casos de prueba
	}
}
