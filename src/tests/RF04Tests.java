package tests;
import org.junit.*;

import asistente.Asistente;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class RF04Tests{
	
	
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
	public void diaDentroDe() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas ser� el s�bado 2 de junio de 2018",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 d�as?")
			);
		Assert.assertEquals(
				"@delucas ser� el martes 31 de julio de 2018",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas ser� el domingo 31 de mayo de 2020",
				shannon.escuchar("@shannon qu� d�a ser� dentro de 2 a�os?")
			);
			
	}
	
	@Test
	public void diaHace() throws ParseException, IOException {
		
	Assert.assertEquals(
				"@delucas fue el mi�rcoles 30 de mayo de 2018",
				shannon.escuchar("@shannon qu� d�a fue ayer?")
			);
		
		Assert.assertEquals(
				"@delucas fue el lunes 28 de mayo de 2018",
				shannon.escuchar("@shannon qu� d�a fue hace 3 d�as?")
			);
		
		Assert.assertEquals(
				"@delucas fue el s�bado 31 de marzo de 2018",
				shannon.escuchar("@shannon qu� d�a fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas fue el martes 31 de mayo de 2016",
				shannon.escuchar("@shannon qu� d�a fue hace 2 a�os?")
			);			
	}
	@Test
	public void tiempoDesde() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas entre el 31 de mayo de 2017 y el 31 de mayo de 2018 pasaron 365 d�as",
				shannon.escuchar("@shannon cu�ntos d�as pasaron desde el 31 de mayo de 2017?")
			);
		Assert.assertEquals(
				"@delucas entre el 30 de abril de 2018 y el 31 de mayo de 2018 pasaron 31 d�as",
				shannon.escuchar("@shannon cu�ntos d�as pasaron desde el 30 de abril?")
			);		 
	}
	
	@Test
	public void tiempoHasta() throws ParseException, IOException {
		Assert.assertEquals(
				"@delucas faltan 2 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el 2 de junio?")
			);
		Assert.assertEquals(
				"@delucas faltan 365 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el 31 de mayo de 2019?")
			);/*
		Assert.assertEquals(
				"@delucas faltan 42 d�as",
				shannon.escuchar("@shannon cu�ntos d�as faltan para el mundial?")
			);*/
	}
	
}
