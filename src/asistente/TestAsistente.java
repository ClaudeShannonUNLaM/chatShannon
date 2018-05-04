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
		
		Assert.assertEquals("Disculpa... no entiendo el pedido, @pepe �podr�as repetirlo?", shannon.escuchar(mensaje));
	}
	
	@Test
	public void saludo() throws ParseException{
		String mensaje = "Hola @shannon";
		
		Assert.assertEquals("�Hola, @pepe!", shannon.escuchar(mensaje));
	}
	@Test
	public void diaDentroDe() throws ParseException {
		Assert.assertEquals(
				"@pepe ser� el s�bado 5 de mayo de 2018",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 d�as?")
			);
		Assert.assertEquals(
				"@pepe ser� el martes 3 de julio de 2018",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe ser� el domingo 3 de mayo de 2020",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 a�os?")
			);
			
	}
	
	@Test
	public void diaHace() throws ParseException {
		
	Assert.assertEquals(
				"@pepe fue el mi�rcoles 2 de mayo de 2018",
				shannon.escuchar("@shannon qu� d�a fue ayer?")
			);
		
		Assert.assertEquals(
				"@pepe fue el lunes 30 de abril de 2018",
				shannon.escuchar("@shannon qu� d�a fue hace 3 d�as?")
			);
		
		Assert.assertEquals(
				"@pepe fue el s�bado 3 de marzo de 2018",
				shannon.escuchar("@shannon qu� d�a fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@pepe fue el martes 3 de mayo de 2016",
				shannon.escuchar("@shannon qu� d�a fue hace 2 a�os?")
			);			
	}
	@Test
	public void tiempoDesde() throws ParseException {
		Assert.assertEquals(
				"@pepe entre el s�bado 29 de abril de 2017 y el jueves 3 de mayo de 2018 pasaron 369 d�as",
				shannon.escuchar("@shannon cu�ntos d�as pasaron desde el 29 de abril de 2017?")
			);
		Assert.assertEquals(
				"@pepe entre el lunes 2 de abril de 2018 y el jueves 3 de mayo de 2018 pasaron 31 d�as",
				shannon.escuchar("@shannon cu�ntos d�as pasaron desde el 2 de abril?")
			);		 
	}
	
	@Test
	public void tiempoHasta() throws ParseException {
		Assert.assertEquals(
				"@pepe faltan 1 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el 5 de mayo?")
			);
		Assert.assertEquals(
				"@pepe faltan 361 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el 30 de abril de 2019?")
			);
		Assert.assertEquals(
				"@pepe faltan 42 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el mundial?")
			);
	}
}
